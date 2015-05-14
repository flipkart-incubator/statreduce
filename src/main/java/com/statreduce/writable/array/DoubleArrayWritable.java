package com.statreduce.writable.array;

import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.DoubleWritable;

public class DoubleArrayWritable extends ArrayWritable {
    public DoubleArrayWritable() {
        super(DoubleWritable.class);
    }

    public DoubleArrayWritable(Double[] values) {
        super(DoubleWritable.class);
        DoubleWritable[] writableValues = new DoubleWritable[values.length];
        for (int i = 0; i < values.length; i++) {
            writableValues[i] = new DoubleWritable(values[i]);
        }
        set(writableValues);
    }
}
