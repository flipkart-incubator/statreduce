package com.statreduce.examples.baseball;

import org.apache.commons.lang.ArrayUtils;

public class BaseballRecordParser {
    String[] header = new String[]{
        "id","playerID","yearID","stint","teamID","lgID","G","G_batting","AB","R","H","X2B","X3B","HR","RBI","SB","CS","BB","SO","IBB","HBP","SH","SF","GIDP","G_old"
    };
    private String[] fields;
    private String playerId;
    private Double[] playerStats;

    public void parse(String record){
        this.fields = record.split(",");
        this.playerId = getValue("playerID");
        this.playerStats = new Double[]{
            getDoubleValue("H"),
            getDoubleValue("BB"),
            getDoubleValue("HBP"),
            getDoubleValue("AB"),
            getDoubleValue("SF")
        };
    }

    public String getPlayerId(){
        return playerId;
    }

    public Double[] getPlayerStats(){
        return playerStats;
    }

    public int indexOf(String column){
        return ArrayUtils.indexOf(header, column);
    }

    public String getValue(String column){
        return this.fields[indexOf(column)];
    }

    public int getIntValue(String column){
        return Integer.parseInt(this.fields[indexOf(column)]);
    }

    public double getDoubleValue(String column){
        String strValue = this.fields[indexOf(column)];
        return "NA".equals(strValue) ? 0.0 : Double.parseDouble(strValue);
    }
}
