package com.statreduce.utils;

import org.apache.commons.io.FileUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class ScriptUtils {
    public static String fromFile(String path) {
        return fromFile(new File(path));
    }

    private static String fromFile(File file) {
        try {
            return FileUtils.readFileToString(file, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error reading file:" + file.getPath());
        }
    }

    public static String fromHDFS(Configuration conf, String path) {
        Path filePath = new Path(path);
        FileSystem fs = null;
        StringBuilder sb = new StringBuilder();
        try {
            fs = FileSystem.get(conf);
            BufferedReader br = new BufferedReader(new InputStreamReader(fs.open(filePath)));
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append(System.getProperty("line.separator"));
                line = br.readLine();
            }
            sb.append(System.getProperty("line.separator"));
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error reading HDFS file:" + path);
        }
    }

    public static String fromResource(String path) {
        ClassLoader classLoader = ScriptUtils.class.getClassLoader();
        File file = new File(classLoader.getResource(path).getFile());
        return fromFile(file);
    }
}
