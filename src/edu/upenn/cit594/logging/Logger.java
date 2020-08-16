package edu.upenn.cit594.logging;

import java.io.*;
import java.util.HashMap;

public class Logger {
    //private PrintWriter out;
    private PrintWriter out;

    private Logger(String filename) {
        try {
            out = new PrintWriter(new File(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    // Singleton Instance
    private static Logger instance = new Logger("log.txt");

    //Singleton accessor method
    public static Logger getInstance()  {
        return instance;
    }


    public void log(String arg1, String arg2,String arg3,String arg4,String arg5 ) {
       long time =  System.currentTimeMillis();
       out.println(time + " " + arg1 + " " + arg2 + " " + arg3 + " " + arg4 + " " + arg5);
       out.flush();
       out.close();
    }
}


