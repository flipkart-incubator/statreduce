package com.statreduce.examples.baseball;

import com.statreduce.utils.DoubleArrayWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class BaseballOBPMapper extends Mapper<LongWritable, Text, Text, DoubleArrayWritable>{
    private BaseballRecordParser parser = new BaseballRecordParser();

    @Override
    protected void map(LongWritable key, Text value, Mapper.Context context) throws IOException, InterruptedException {
        parser.parse(value.toString());
        context.write(new Text(parser.getPlayerId()), new DoubleArrayWritable(parser.getPlayerStats()));
    }
}
