package com.statreduce.utils;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;

import java.util.ArrayList;
import java.util.List;

public class WritableUtils {
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

    public static List<Long> toLongList(Iterable<LongWritable> values) {
        List<Long> list = new ArrayList<Long>();
        for (LongWritable value : values) {
            list.add(new Long(value.get()));
        }
        return list;
    }

    public static DoubleWritable[] toWritableArray(Double[] array) {
        DoubleWritable[] writableArray = new DoubleWritable[array.length];
        for(int i=0; i<array.length; i++){
            writableArray[i] = new DoubleWritable(array[i]);
        }
        return writableArray;
    }


}
