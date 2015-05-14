package com.statreduce.utils;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {
    public static List<Double> toDoubleList(Iterable<DoubleWritable> values) {
        List<Double> list = new ArrayList<Double>();
        for (DoubleWritable value : values) {
            list.add(new Double(value.get()));
        }
        return list;
    }

    public static List<Integer> toIntList(Iterable<IntWritable> values) {
        List<Integer> list = new ArrayList<Integer>();
        for (IntWritable value : values) {
            list.add(new Integer(value.get()));
        }
        return list;
    }
}
