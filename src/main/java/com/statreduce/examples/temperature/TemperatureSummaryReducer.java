package com.statreduce.examples.temperature;

import com.statreduce.reducer.ListStatReducer;

public class TemperatureSummaryReducer extends ListStatReducer {
    public TemperatureSummaryReducer(){
        super("tempValues", "avgTemp", "avgTemp <- mean(tempValues)", Double.class, Double.class);
    }
}
