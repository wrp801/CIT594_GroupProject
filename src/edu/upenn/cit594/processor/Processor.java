package edu.upenn.cit594.processor;

import edu.upenn.cit594.data.Property;
import edu.upenn.cit594.data.Violation;
import edu.upenn.cit594.data.Zipcode;
import edu.upenn.cit594.data.Zipcode;
import edu.upenn.cit594.datamanagement.PropertyReader;
import edu.upenn.cit594.datamanagement.ViolationReader;
import edu.upenn.cit594.datamanagement.ZipcodeReader;
import edu.upenn.cit594.datamanagement.ZipcodeReader;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Processor {

    /**
     *
     * 1. Use the Singleton design pattern to implement the file logger in Step #7.
     *
     * 2. Use the Strategy design pattern to implement the average residential market
     *      value (Step #3) and average residential total living area (Step #4) features. Think
     *      about what similarities these two features have and how you can specify different
     *      “strategies” for each.
     *
     */

    protected ZipcodeReader zipCodeReader;
    protected HashMap<Integer, Zipcode> zipCodes;

    protected ViolationReader violationReader;
    protected HashMap<Integer, Violation> violations;

    protected PropertyReader propertyReader;
    protected HashMap<Integer, Property> properties;

    // Property reader?

    // Other reader instance vars to come later

    public Processor (ZipcodeReader zipCodeReader, ViolationReader violationReader, PropertyReader propertyReader) throws IOException, ParseException, org.json.simple.parser.ParseException {

        this.zipCodeReader = zipCodeReader;
        zipCodes = zipCodeReader.read();

        this.violationReader = violationReader;
        violations = violationReader.read();

        this.propertyReader = propertyReader;
        properties = propertyReader.read();

    }

    public void displayTotalPopulation() {

        int popSum = 0;
        for (Integer zipCode : zipCodes.keySet()) popSum += zipCodes.get(zipCode).getPopulation();
        System.out.println(popSum);
    }

    public void displayFinesPerCapita() {

//        functionality still needed:
//            - ensure ascending zip sort
//            - ignore violations from unknown zip (I think it does this already or maybe breaks)
//            - truncate values to four-digit precision

        for (Violation violation : violations.values()) {

            try {
                int violationZip = violation.getZipCode();
                zipCodes.get(violationZip).setTotalFines(zipCodes.get(violationZip).getTotalFines() + violation.getFine());
            } catch (NullPointerException ignored) {
            }

        }

        for (Zipcode zipcode : zipCodes.values()) {
            double finesPerCapita = zipcode.getTotalFines() / zipcode.getPopulation();
            System.out.println(zipcode.getZipcode() + " " + finesPerCapita + "\n");
        }

        System.out.println("");
    }

    public void displayAvgMarketValue(int enteredZip) {

        // DUMMY values

//        List<Property> properties = new ArrayList<>();

        AverageMarketValue averageMarketValue = new AverageMarketValue();
        averageMarketValue.displayAverage(properties, enteredZip);

    }

    public void displayAvgTotalLivableArea(int enteredZip) {

        AverageLivableArea averageLivableArea = new AverageLivableArea();
        averageLivableArea.displayAverage(properties, enteredZip);

    }

    public void displayMarketValuePerCapita(int enteredZip) {

        double totalValue = 0.0;
        int numProperties = 0; // this is probably better design than instance variables in the Property class

        for (Property property : properties.values()) {

            try {
                totalValue += property.getMarketValue();
                numProperties ++ ;
            } catch (NullPointerException ignored) {
            }
        }

        System.out.println(totalValue / numProperties);
        System.out.println("");

    }

    public void getPopulation(int enteredZip) { // use for debugging
        System.out.println(zipCodes.get(enteredZip).getPopulation());
    }

//    protected ZipcodeReader zipCodeReader;
//    protected List<Zipcode> zipCodes;
//
//    // Other reader instance vars to come later
//
//    public Processor (ZipcodeReader zipCodeReader) {
//
//        this.zipCodeReader = zipCodeReader;
//        zipCodes = zipCodeReader.read();
//
//    }
//
//    public void displayTotalPopAllZipCodes() {
//
//        int popSum = 0;
//        for (Zipcode zipCode : zipCodes) popSum += zipCode.getPopulation();
//        System.out.println(popSum);
//    }


}