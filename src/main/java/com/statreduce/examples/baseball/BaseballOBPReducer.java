package com.statreduce.examples.baseball;

import com.statreduce.reducer.MatrixStatReducer;
import com.statreduce.utils.ScriptUtils;

public class BaseballOBPReducer extends MatrixStatReducer {
    public BaseballOBPReducer() {
        super("playerStats", "obp", ScriptUtils.fromFile("/tmp/on_base_percentage.R"),
                "obp <- calculateOBP(playerStats)", Double.class, Double.class);
    }
}
