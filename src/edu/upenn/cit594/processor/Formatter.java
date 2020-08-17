package edu.upenn.cit594.processor;
import java.util.regex.*;

public class Formatter {

    public static String concat(String file, String fileType) {
        Pattern p = Pattern.compile("\\.+");
        Matcher m = p.matcher(file);
        if (m.find()) {
            return file;
        }
        else {
            StringBuilder sb = new StringBuilder();
            sb.append(file).append(".").append(fileType);
            return sb.toString();
        }
    }
}
