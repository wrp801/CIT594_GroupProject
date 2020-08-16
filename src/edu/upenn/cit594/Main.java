package edu.upenn.cit594;

import edu.upenn.cit594.datamanagement.PropertyReader;
import edu.upenn.cit594.datamanagement.ViolationReader;
import edu.upenn.cit594.datamanagement.ZipcodeReader;
import edu.upenn.cit594.processor.AverageMarketValue;
import edu.upenn.cit594.processor.Processor;
import edu.upenn.cit594.logging.Logger;

import java.io.IOException;
import java.sql.SQLOutput;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws ParseException, org.json.simple.parser.ParseException, IOException {

        ZipcodeReader zipcodeReader = new ZipcodeReader("population.txt");
        ViolationReader violationReader = new ViolationReader("parking.csv", "csv");
        PropertyReader propertyReader = new PropertyReader("properties.csv");
        Processor processor = new Processor(zipcodeReader, violationReader, propertyReader);

        Logger l = Logger.getInstance();
        l.log("1","2","3","4","5");

        processor.displayTotalPopulation();

        System.out.println("\n");
        processor.displayFinesPerCapita();

        System.out.println("\nMarket value:");
        processor.displayAvgMarketValue(19133);

        System.out.println("\nPopulation: ");
        processor.getPopulation(19133);

        System.out.println("\nLivable area:");
        processor.displayAvgTotalLivableArea(19133);

        System.out.println("\nMarket value per capita:");
        processor.displayMarketValuePerCapita(19133);

<<<<<<< HEAD
        System.out.println("\nFine vs market ranking:");
        processor.displayFinesVsMarketValue();
=======
>>>>>>> 5c4f2f4953e25ac635221043b5bdf8292a8d12cc

        // The code block below should replace the dummy block above when finished

//        if (args.length != 5) {
//            System.out.println("Please include exactly 5 runtime arguments.");
//            System.exit(0);
//        }

        // This can go in UI or Processor if we think that's more appropriate
        // Needs additional error-checking functionality

//        String violationFileFormat = args[0];
//        String violationFileName = args[1];
//        String propertyFileName = args[2];
//        String populationFileName = args[3];
//        String logFileName = args[4];


    }
}
