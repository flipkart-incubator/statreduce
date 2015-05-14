package com.statreduce.writable.array;

import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.IntWritable;

public class IntArrayWritable extends ArrayWritable {
    public IntArrayWritable() {
        super(IntWritable.class);
    }

    public IntArrayWritable(Integer[] values) {
        super(IntWritable.class);
        IntWritable[] writableValues = new IntWritable[values.length];
        for (int i = 0; i < values.length; i++) {
            writableValues[i] = new IntWritable(values[i]);
        }
        set(writableValues);
    }
}
