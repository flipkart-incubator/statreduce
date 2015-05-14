package com.statreduce.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class ScriptUtils {
    public static String fromFile(String path){
        try {
            return FileUtils.readFileToString(new File(path), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error reading file:" + path);
        }
    }
}
