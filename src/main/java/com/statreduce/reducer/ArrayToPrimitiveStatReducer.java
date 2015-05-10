package com.statreduce.reducer;

import org.apache.hadoop.io.Writable;

public class ArrayToPrimitiveStatReducer<K extends Writable,V extends Writable> extends AbstractStatReducer<K,V> {
    private String inputVar;
    private String outputVar;
    private String rFunctionCall;
    private Class inputType;
    private Class outputType;

    public ArrayToPrimitiveStatReducer(String inputVar, String outputVar, String rFunctionCall, Class inputType, Class outputType) {
        this.inputVar = inputVar;
        this.outputVar = outputVar;
        this.rFunctionCall = rFunctionCall;
        this.inputType = inputType;
        this.outputType = outputType;
    }

    @Override
    protected String getInputVar() {
        return inputVar;
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
