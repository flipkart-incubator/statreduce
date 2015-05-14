package com.statreduce.examples.temperature;

import org.apache.hadoop.io.Text;

public class NcdcRecordParser {

    private String year;
    private int temperature;
    private String quality;

    public void parse(String record){
        year = record.substring(15, 19);
        String temperatureString;
        if (record.charAt(87) == '+'){
            temperatureString = record.substring(88, 92);
        } else {
            temperatureString = record.substring(87, 92);
        }
        temperature = Integer.parseInt(temperatureString);
        quality = record.substring(92, 93);
    }

    public void parse(Text record){
        parse(record.toString());
    }

    public boolean isValidTemperature(){
        return temperature != 9999 && quality.matches("[01459]");
    }

    public String getYear() {
        return year;
    }

    public int getTemperature() {
        return temperature;
    }

}
