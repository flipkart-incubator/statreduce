package com.statreduce.examples.baseball;

import com.statreduce.utils.DoubleArrayWritable;
import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.Reducer;
import org.ddahl.jvmr.RInJava;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//public class BaseballOBPReducer<K extends Writable, V extends Writable> extends Reducer<K, ArrayWritable, K, V> {
public class BaseballOBPReducer extends Reducer<Text, DoubleArrayWritable, Text, DoubleWritable> {
    RInJava R;

    public BaseballOBPReducer() {
        R = new RInJava();
    }

    @Override
    protected void reduce(Text key, Iterable<DoubleArrayWritable> values, Context context) throws IOException, InterruptedException {
        initializeRFunction();
        assignRInput(values);
        invokeR();
        context.write(key, (DoubleWritable) getROutputAsWritable());
    }

    private void initializeRFunction() {
        R.eval(getRFunction());
    }

    private void assignRInput(Iterable<DoubleArrayWritable> values) {
        R.update(getInputVar(), toMatrix(getInputType(), values));
    }

    private double[][] toMatrix(Class inputType, Iterable<DoubleArrayWritable> rows) {
        int rowCount = 0;
        int colCount = 0;
        List<Writable[]> rowList = new ArrayList<Writable[]>();
        for (ArrayWritable row : rows) {
            Writable[] elements = row.get();
            rowList.add(elements);
            colCount = elements.length;
            rowCount += 1;
        }
        double[][] matrix = new double[rowCount][colCount];
        for(int i=0; i<rowCount; i++){
            for(int j=0; j<colCount; j++){
                matrix[i][j] = ((DoubleWritable)rowList.get(i)[j]).get();
            }
        }
        return matrix;
    }

    private void invokeR() {
        R.eval(getRFunctionCall());
    }

    private Writable getROutputAsWritable() {
//        if(getOutputType() == Double.class){
            return new DoubleWritable((Double) getROutput());
//        } else if (getOutputType() == Integer.class) {
//            return new IntWritable((Integer) getROutput());
//        } else {
//            throw new IllegalArgumentException("Invalid output value type in StatReducer: " + getOutputType());
//        }
    }

    private Object getROutput() {
//        if(getOutputType() == Double.class){
            return R.toPrimitiveDouble(getOutputVar());
//        } else if (getOutputType() == Integer.class){
//            return R.toPrimitiveInt(getOutputVar());
//        } else {
//            throw new IllegalArgumentException("Invalid output value type in StatReducer: " + getOutputType());
//        }
    }

    protected String getInputVar(){
        return "playerStats";
    }

    protected String getRFunction(){
        return "calculateOBP <- function(playerStats){" +
                "colnames(playerStats) <- c(\"H\", \"BB\", \"HBP\", \"AB\", \"SF\")\n" +
                "h <- sum(playerStats[,\"H\"])\n" +
                "bb <- sum(playerStats[,\"BB\"])\n" +
                "hbp <- sum(playerStats[,\"HBP\"])\n" +
                "ab <- sum(playerStats[,\"AB\"])\n" +
                "sf <- sum(playerStats[,\"SF\"])\n" +
                "obp <- (h + bb + hbp) / (ab + bb + hbp + sf)\n" +
                "obp\n" +
                "}";
    }
    protected String getRFunctionCall(){
        return "obp <- calculateOBP(playerStats)";
    }

    protected String getOutputVar(){
        return "obp";
    }

    protected Class getInputType(){
        return Double.class;
    }

    protected Class getOutputType(){
        return Double.class;
    }
}
