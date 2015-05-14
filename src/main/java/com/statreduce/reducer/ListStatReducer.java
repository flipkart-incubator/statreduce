package com.statreduce.reducer;

import com.google.common.primitives.Doubles;
import com.google.common.primitives.Ints;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Writable;

import static com.statreduce.utils.ListUtils.toDoubleList;
import static com.statreduce.utils.ListUtils.toIntList;

public class ListStatReducer<K extends Writable,V extends Writable> extends AbstractStatReducer<K,V> {
    private String inputVar;
    private String outputVar;
    private String rFunction;
    private String rFunctionCall;
    private Class inputType;
    private Class outputType;

    public ListStatReducer(String inputVar, String outputVar, String rFunction, String rFunctionCall, Class inputType, Class outputType) {
        this.inputVar = inputVar;
        this.outputVar = outputVar;
        this.rFunction = rFunction;
        this.rFunctionCall = rFunctionCall;
        this.inputType = inputType;
        this.outputType = outputType;
    }

    @Override
    protected Object getRInput(Iterable<V> values){
        if(getInputType() == Double.class){
            return Doubles.toArray(toDoubleList((Iterable<DoubleWritable>) values));
        } else if (getInputType() == Integer.class){
            return Ints.toArray(toIntList((Iterable<IntWritable>) values));
        } else {
            throw new IllegalArgumentException("Invalid input value type in ListStatReducer: " + getInputType());
        }
    }

    @Override
    protected String getInputVar() {
        return inputVar;
    }

    @Override
    protected String getRFunction() {
        return rFunction;
    }

    @Override
    protected String getRFunctionCall() {
        return rFunctionCall;
    }

    @Override
    protected String getOutputVar() {
        return outputVar;
    }

    @Override
    public Class getInputType() {
        return inputType;
    }

    @Override
    public Class getOutputType() {
        return outputType;
    }
}
