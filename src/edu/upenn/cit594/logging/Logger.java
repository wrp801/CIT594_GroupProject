package edu.upenn.cit594.logging;

import java.io.*;
import java.util.HashMap;

public class Logger {
    //private PrintWriter out;
    private PrintWriter out;

//    private Logger(String filename) {
//        try {
//            out = new PrintWriter(new File(filename));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    // Singleton Instance
//    private static Logger instance = new Logger("log.txt");
//
//    //Singleton accessor method
//    public static Logger getInstance()  {
//        return instance;
//    }
//
//
//    public void log(String arg1, String arg2,String arg3,String arg4,String arg5 ) {
//        long time =  System.currentTimeMillis();
//        out.println(time + " " + arg1 + " " + arg2 + " " + arg3 + " " + arg4 + " " + arg5);
//        out.flush();
//        out.close();
//    }

    private Logger() {

    }

    private static Logger instance = new Logger();
    public static Logger getInstance() {
        return instance;
    }

    public void log (String filename,String arg1, String arg2, String arg3, String arg4, String arg5) {
        File f = new File(filename);
        if (f.isFile()) {
            try {
                FileWriter fw = new FileWriter(filename, true);
                long time =  System.currentTimeMillis();
                fw.write(time + " " + arg1 + " " + arg2 + " " + arg3 + " " + arg4 + " " + arg5);
                fw.close();
            }
            catch (IOException e) {
                System.out.println("Error in appending data to the log file");
                System.exit(1);
            }
        }
        else {
            try {
                out = new PrintWriter(new File(filename));
                long time =  System.currentTimeMillis();
                out.println(time + " " + arg1 + " " + arg2 + " " + arg3 + " " + arg4 + " " + arg5);
                out.flush();
                out.close();
            }
            catch (FileNotFoundException e) {
                System.out.println("Error in creating the log file");
                System.exit(1);
            }
        }
    }

    public void log(String filename,String inputFile) {
        try {
            FileWriter fw = new FileWriter(filename, true);
            long time =  System.currentTimeMillis();
            fw.write("\n" + time + " " + inputFile);
            fw.close();
        }
        catch (IOException e) {
            System.out.println("Error in appending data to the log file");
            System.exit(1);
        }
    }
    public void log(String filename,int entry) {
        try {
            FileWriter fw = new FileWriter(filename, true);
            long time =  System.currentTimeMillis();
            fw.write("\n" + time + " " + entry);
            fw.close();
        }
        catch (IOException e) {
            System.out.println("Error in appending data to the log file");
            System.exit(1);
        }

    }

}