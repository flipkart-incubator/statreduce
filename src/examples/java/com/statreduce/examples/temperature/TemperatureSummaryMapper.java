package com.statreduce.examples.temperature;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class TemperatureSummaryMapper extends Mapper<LongWritable, Text, Text, DoubleWritable> {

    private NcdcRecordParser parser = new NcdcRecordParser();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        parser.parse(value.toString());
        if(parser.isValidTemperature()){
            context.write(new Text(parser.getYear()), new DoubleWritable(parser.getTemperature()));
        }
    }
}
