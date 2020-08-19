package edu.upenn.cit594.logging;

import java.io.*;
import java.util.HashMap;

public class Logger {
    private PrintWriter out;
    private Logger() {

    }

    private static Logger instance = new Logger();
    public static Logger getInstance() {
        return instance;
    }

    /**
     * Create a log file (if one doesn't exist) or append to a log file
     * @param filename name of the log file
     * @param arg1 runtime argument 1
     * @param arg2 runtime arg 2
     * @param arg3 runtime arg 3
     * @param arg4 runtime arg 4
     * @param arg5 runtime arg 5
     */
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

    /**
     * Append a string to the log file
     * @param filename name of the log file
     * @param inputFile name of the input file being read in
     */

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

    /**
     * Append an int to the log file
     * @param filename log file name
     * @param entry integer to write to the file
     */
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