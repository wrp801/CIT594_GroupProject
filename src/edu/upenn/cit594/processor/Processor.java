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
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.*;

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

    // Instance variables

    protected ZipcodeReader zipCodeReader;
    protected HashMap<Integer, Zipcode> zipCodes;

    protected ViolationReader violationReader;
    protected HashMap<Integer, Violation> violations;

    protected PropertyReader propertyReader;
    protected HashMap<Integer, Property> properties;

    // Memoization

    private int results1;
    private TreeMap<Integer, Double> results2;
    private HashMap<Integer, Integer> results3;
    private HashMap<Integer, Integer> results4;
    private HashMap<Integer, Integer> results5;
    private HashMap<Integer, double[]> results6;

    // Constructor

    public Processor (ZipcodeReader zipCodeReader, ViolationReader violationReader, PropertyReader propertyReader) throws IOException, ParseException, org.json.simple.parser.ParseException {

        this.zipCodeReader = zipCodeReader;
        zipCodes = zipCodeReader.read();

        this.violationReader = violationReader;
        violations = violationReader.read();

        this.propertyReader = propertyReader;
        properties = propertyReader.read();

        results1 = 0;
        results2 = new TreeMap<>();
        results3 = new HashMap<>();
        results4 = new HashMap<>();
        results5 = new HashMap<>();
        results6 = new HashMap<>();

    }

    // Problem 1

    public int displayTotalPopulation() {

        if (results1 != 0) {
            return results1;
        } else {
            int popSum = 0;
            for (Integer zipCode : zipCodes.keySet()) popSum += zipCodes.get(zipCode).getPopulation();
            results1 = popSum;
            return popSum;
        }
    }

    // Problem 2

    public TreeMap<Integer, Double> displayFinesPerCapita() {

//        functionality still needed:
//            - ensure ascending zip sort
//            - ignore violations from unknown zip (I think it does this already or maybe breaks)
//            - truncate values to four-digit precision

        if (results2.size() > 0) return results2;

        else {

            TreeMap<Integer, Double> zipFines = new TreeMap<>(); // todo: fine should be a Double

            for (Violation violation : violations.values()) {

                if (violation.getZipCode() >= 10000 && violation.getState().equals("PA")) {
                    try {
                        int violationZip = violation.getZipCode();
                        if (!zipFines.containsKey(violationZip)) {
                            zipFines.put(violationZip, (double) violation.getFine());
                        } else {
                            zipFines.put(violationZip, zipFines.get(violationZip) + (double) violation.getFine());
                        }
                    } catch (NullPointerException ignored) {
                    }
                }
            }

            for (int zipcode : zipFines.keySet()) {
                try {

                    double finesPerCapita = (double) zipFines.get(zipcode) / zipCodes.get(zipcode).getPopulation();
                    zipFines.put(zipcode, finesPerCapita);

                } catch (NullPointerException ignored) {
                }
            }
            results2 = zipFines;
            return zipFines;
        }
    }

    // Problem 3

    public int displayAvgMarketValue(int enteredZip) {

        if (results3.containsKey(enteredZip)) return results3.get(enteredZip);
        else {

            AverageMarketValue averageMarketValue = new AverageMarketValue();
            int returnValue = averageMarketValue.displayAverage(properties, enteredZip);
            results3.put(enteredZip, returnValue);
            return returnValue;
        }

    }

    // Problem 4

    public int displayAvgTotalLivableArea(int enteredZip) {

        if (results4.containsKey(enteredZip)) return results4.get(enteredZip);
        else {
            AverageLivableArea averageLivableArea = new AverageLivableArea();
            int returnValue = averageLivableArea.displayAverage(properties, enteredZip);
            results4.put(enteredZip, returnValue);
            return returnValue;
        }

    }

    // Problem 5

    public int displayMarketValuePerCapita(int enteredZip) {

        if (results5.containsKey(enteredZip)) return results5.get(enteredZip);

        else {

            double totalValue = 0.0;
            int numProperties = 0; // this is probably better design than instance variables in the Property class

            for (Property property : properties.values()) {
                try {
                    if (property.getZipcode() == enteredZip) {
//                    System.out.println("VALUE: " + property.getMarketValue() + " | LIVABLE SPACE: " + property.getTotalLiveableArea());
                        totalValue += property.getMarketValue();
                        numProperties++;
                    }
                } catch (NullPointerException ignored) {
                }
            }
            try {
                int returnValue = (int) Math.floor(totalValue / zipCodes.get(enteredZip).getPopulation());
                results5.put(enteredZip, returnValue);
                return returnValue;
            } catch (NullPointerException e) {
                results5.put(enteredZip, 0);
                return 0;
            }
        }
    }

    // Problem 6

    public HashMap<Integer, double[]> displayFinesVsMarketValue() {

        if (results6.size() > 0) return results6;

        else {

            HashMap<Integer, double[]> zipFinesAndValues = new HashMap<>();

            // get fines per capita
            for (Violation violation : violations.values()) {
//            System.out.println("ZIP: " + violation.getZipCode());
                if (violation.getZipCode() >= 10000) {
//                System.out.println("ZIP = " + violation.getZipCode());
                    try {
                        int violationZip = violation.getZipCode();
                        if (!zipFinesAndValues.containsKey(violationZip)) {
                            zipFinesAndValues.put(violationZip, new double[]{violation.getFine(), 0, 0, 0});
                        } else {
                            zipFinesAndValues.get(violationZip)[0] += violation.getFine();
                            //                    zipFinesAndValues.put(violationZip, zipFinesAndValues.get(violationZip)[0] + violation.getFine());
                        }
                    } catch (NullPointerException ignored) {
                    }
                }
            }

            // get market value per capita
            // can we combine with previous function or does that have to be a void that prints only?

            double totalValue = 0.0;
            int numProperties = 0; // this is probably better design than instance variables in the Property class

            for (Property property : properties.values()) {
                if (property.getZipcode() >= 10000) {
                    try {
                        int propertyZip = property.getZipcode();
                        if (!zipFinesAndValues.containsKey(propertyZip)) {
                            zipFinesAndValues.put(propertyZip, new double[]{0, property.getMarketValue(), 0, 0});
                        } else {
                            zipFinesAndValues.get(propertyZip)[1] += property.getMarketValue();
//                    zipFinesAndValues.put(violationZip, zipFinesAndValues.get(violationZip)[0] + violation.getFine());
                        }
                    } catch (NullPointerException ignored) {
                    }
                }
            }

            for (int zipCode : zipFinesAndValues.keySet()) {
                double finesPerCapita = 0;
                double valuePerCapita = 0;
                try {
                    zipFinesAndValues.get(zipCode)[0] /= zipCodes.get(zipCode).getPopulation();
                    zipFinesAndValues.get(zipCode)[1] /= zipCodes.get(zipCode).getPopulation();
                } catch (NullPointerException ignored) {
                    zipFinesAndValues.get(zipCode)[0] = 0;
                    zipFinesAndValues.get(zipCode)[1] = 0;
                }
            }

            for (int zipCode : zipFinesAndValues.keySet()) {
                int fineRankCounter = zipFinesAndValues.size();
                int valueRankCounter = zipFinesAndValues.size();

                for (double[] zipInfo : zipFinesAndValues.values()) {
                    if (zipInfo[0] < zipFinesAndValues.get(zipCode)[0]) {
                        fineRankCounter--;
                    }
                    if (zipInfo[1] < zipFinesAndValues.get(zipCode)[1]) {
                        valueRankCounter--;
                    }
                }

                zipFinesAndValues.get(zipCode)[2] = fineRankCounter;
                zipFinesAndValues.get(zipCode)[3] = valueRankCounter;

            }

            results6 = zipFinesAndValues;
            return zipFinesAndValues;
        }
    }


    // HELPER FUNCTIONS

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