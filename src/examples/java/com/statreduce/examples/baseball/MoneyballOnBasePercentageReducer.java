package com.statreduce.examples.baseball;

import com.statreduce.reducer.MatrixStatReducer;
import com.statreduce.utils.ScriptUtils;

public class MoneyballOnBasePercentageReducer extends MatrixStatReducer {
    public MoneyballOnBasePercentageReducer() {
        super("playerStats", "obp", ScriptUtils.fromFile("/tmp/on_base_percentage.R"),
                "obp <- calculateOBP(playerStats)", Double.class, Double.class);
    }
}
