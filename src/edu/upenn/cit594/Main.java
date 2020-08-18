package edu.upenn.cit594;

import edu.upenn.cit594.datamanagement.PropertyReader;
import edu.upenn.cit594.datamanagement.ViolationReader;
import edu.upenn.cit594.datamanagement.ZipcodeReader;
import edu.upenn.cit594.processor.AverageMarketValue;
import edu.upenn.cit594.processor.Formatter;
import edu.upenn.cit594.processor.Processor;
import edu.upenn.cit594.logging.Logger;
import edu.upenn.cit594.ui.UserInterface;

import java.io.IOException;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws ParseException, org.json.simple.parser.ParseException, IOException {

        /*
        Command Line Arguments are:
        1: The format of the parking violations file (csv or json)
        2: The name of the parking violations input file
        3: The name of the property values input file
        4: The name of the population input file
        5: The name of the log file

        If that is successful then prompt the user to enter a number 0-6

        0: If entered, exit the program
        1: If entered, show the total population for all users
        2: If entered, show the total parking fines per capita for each zip code
        3: If entered, show the average market value for residences in a specified zip code
        4: If entered, show the average total livable area for residences in a specified zip code
        5: If entered, show the total residential market value per capita for a specified zip code
        6: If entered, show the results for the custom computation
         */


        if (args.length != 5) {
            System.out.println("Please include exactly 5 runtime arguments.");
            System.exit(0);
        }



        String violationFileFormat = args[0];
        String violationFileName = args[1];
        String propertyFileName = args[2];
        String populationFileName = args[3];
        String logFileName = args[4];

        String violationFile = Formatter.concat(violationFileName,violationFileFormat);
        String propertyFile = Formatter.concat(propertyFileName,"csv");
        String populationFile = Formatter.concat(populationFileName,"txt");
        String logFile = Formatter.concat(logFileName,"txt");

        Logger l = Logger.getInstance();
        l.log(args[0],args[1],args[2],args[3],args[4]);

        ZipcodeReader zipcodeReader = new ZipcodeReader(populationFile);
        ViolationReader violationReader = new ViolationReader(violationFile, args[0]);
        PropertyReader propertyReader = new PropertyReader(propertyFile);
        Processor processor = new Processor(zipcodeReader, violationReader, propertyReader);




        //Declare User Interface
        UserInterface ui = new UserInterface(processor);
        ui.start();
    }
}
