package edu.upenn.cit594.processor;

public class Formatter {

    public static String concat(String file, String fileType) {
        StringBuilder sb = new StringBuilder();
        sb.append(file).append(".").append(fileType);
        return sb.toString();
    }
}
