package edu.upenn.cit594.processor;

//import edu.upenn.cit594.data.ZipCode;
import edu.upenn.cit594.data.Zipcode;
//import edu.upenn.cit594.datamanagement.ZipCodeReader;
import edu.upenn.cit594.datamanagement.ZipcodeReader;

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
    protected List<Zipcode> zipCodes;

    // Other reader instance vars to come later
/*
    public Processor (ZipcodeReader zipCodeReader) {

        this.zipCodeReader = zipCodeReader;
        zipCodes = zipCodeReader.getAllZipCodes();

    }

//    /**
//     * Dummy constructor until ZipCodeReader is created
//     */
//
//    public Processor (List<ZipCode> zipCodes) {
//
//        this.zipCodes = zipCodes;
//
//    }
/*
    public void displayTotalPopAllZipCodes() {

        int popSum = 0;
        for (ZipCode zipCode : zipCodes) popSum += zipCode.getPop();
        System.out.println(popSum);
    }


 */


}
