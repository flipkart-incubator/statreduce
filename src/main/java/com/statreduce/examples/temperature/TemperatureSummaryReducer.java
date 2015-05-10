package com.statreduce.examples.temperature;

import com.statreduce.reducer.ArrayToPrimitiveStatReducer;

public class TemperatureSummaryReducer extends ArrayToPrimitiveStatReducer {
    public TemperatureSummaryReducer(){
        super("tempValues", "avgTemp", "avgTemp <- mean(tempValues)", Double.class, Double.class);
    }
}
