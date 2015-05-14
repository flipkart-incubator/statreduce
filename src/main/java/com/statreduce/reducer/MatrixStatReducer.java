package com.statreduce.reducer;

import com.statreduce.utils.MatrixUtils;
import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.Writable;

import java.util.ArrayList;
import java.util.List;

public class MatrixStatReducer<K extends Writable,V extends Writable> extends AbstractStatReducer<K,V> {
    private String inputVar;
    private String outputVar;
    private String rFunction;
    private String rFunctionCall;
    private Class inputType;
    private Class outputType;

    public MatrixStatReducer(String inputVar, String outputVar, String rFunction, String rFunctionCall, Class inputType, Class outputType) {
        this.inputVar = inputVar;
        this.outputVar = outputVar;
        this.rFunction = rFunction;
        this.rFunctionCall = rFunctionCall;
        this.inputType = inputType;
        this.outputType = outputType;
    }


    @Override
    protected Object getRInput(Iterable<V> values) {
        int rowCount = 0;
        int colCount = 0;
        List<Writable[]> rowList = new ArrayList<Writable[]>();
        for (ArrayWritable row : ((Iterable<ArrayWritable>)values)) {
            Writable[] elements = row.get();
            rowList.add(elements);
            colCount = elements.length;
            rowCount += 1;
        }
        if(getInputType() == Double.class) {
            return MatrixUtils.toDoubleMatrix(rowCount, colCount, rowList);
        } else if(getInputType() == Integer.class) {
            return MatrixUtils.toIntMatrix(rowCount, colCount, rowList);
        } else  {
            throw new IllegalArgumentException("Invalid input type in MatrixStatReducer:" + getInputType());
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

    public void setrFunction(String rFunction) {
        this.rFunction = rFunction;
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
