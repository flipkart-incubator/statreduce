package com.statreduce.reducer;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.Reducer;
import org.ddahl.jvmr.RInJava;

import java.io.IOException;

import static com.google.common.primitives.Doubles.toArray;
import static com.google.common.primitives.Ints.toArray;
import static com.statreduce.utils.WritableUtils.toDoubleList;
import static com.statreduce.utils.WritableUtils.toIntList;

public abstract class AbstractStatReducer<K extends Writable, V extends Writable> extends Reducer<K, V, K, V> {
    RInJava R;

    public AbstractStatReducer() {
        R = new RInJava();
    }

    @Override
    protected void reduce(K key, Iterable<V> values, Context context) throws IOException, InterruptedException {
        assignRInput(values);
        invokeR();
        context.write(key, (V) getROutputAsWritable());
    }

    private void assignRInput(Iterable<V> values) {
        if(getInputType() == Double.class){
            R.update(getInputVar(), toArray(toDoubleList((Iterable<DoubleWritable>) values)));
        } else if (getInputType() == Integer.class){
            R.update(getInputVar(), toArray(toIntList((Iterable<IntWritable>) values)));
        } else {
            throw new IllegalArgumentException("Invalid input value type in StatReducer: " + getInputType());
        }
    }

    private void invokeR() {
        R.eval(getRFunctionCall());
    }

    private Writable getROutputAsWritable() {
        if(getOutputType() == Double.class){
            return new DoubleWritable((Double) getROutput());
        } else if (getOutputType() == Integer.class) {
            return new IntWritable((Integer) getROutput());
        } else {
            throw new IllegalArgumentException("Invalid output value type in StatReducer: " + getOutputType());
        }
    }

    private Object getROutput() {
        if(getOutputType() == Double.class){
            return R.toPrimitiveDouble(getOutputVar());
        } else if (getOutputType() == Integer.class){
            return R.toPrimitiveInt(getOutputVar());
        } else {
            throw new IllegalArgumentException("Invalid output value type in StatReducer: " + getOutputType());
        }
    }

    protected abstract String getInputVar();

    protected abstract String getRFunctionCall();

    protected abstract String getOutputVar();

    protected abstract Class getInputType();

    protected abstract Class getOutputType();
}
