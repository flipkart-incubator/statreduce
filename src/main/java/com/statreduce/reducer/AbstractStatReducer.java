package com.statreduce.reducer;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.Reducer;
import org.ddahl.jvmr.RInJava;

import java.io.IOException;

public abstract class AbstractStatReducer<K extends Writable, V extends Writable> extends Reducer<K, V, K, V> {
    RInJava R;

    public AbstractStatReducer() {
        R = new RInJava();
    }

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        initializeRFunction();
    }

    @Override
    protected void reduce(K key, Iterable<V> values, Context context) throws IOException, InterruptedException {
        assignRInput(values);
        invokeR();
        context.write(key, (V) getROutputAsWritable());
    }

    private void initializeRFunction() {
        R.eval(getRFunction());
    }

    private void assignRInput(Iterable<V> values) {
        R.update(getInputVar(), getRInput(values));
    }

    protected abstract Object getRInput(Iterable<V> values);

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

    protected abstract String getRFunction();

    protected abstract String getRFunctionCall();

    protected abstract String getOutputVar();

    protected abstract Class getInputType();

    protected abstract Class getOutputType();
}
