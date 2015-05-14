package com.statreduce.utils;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Writable;

import java.util.List;

public class MatrixUtils {
    public static double[][] toDoubleMatrix(int rowCount, int colCount, List<Writable[]> rowList) {
        double[][] matrix = new double[rowCount][colCount];
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                matrix[i][j] = ((DoubleWritable) rowList.get(i)[j]).get();
            }
        }
        return matrix;
    }

    public static int[][] toIntMatrix(int rowCount, int colCount, List<Writable[]> rowList) {
        int[][] matrix = new int[rowCount][colCount];
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                matrix[i][j] = ((IntWritable) rowList.get(i)[j]).get();
            }
        }
        return matrix;
    }


}
