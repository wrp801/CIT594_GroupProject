package edu.upenn.cit594;

import edu.upenn.cit594.datamanagement.ZipcodeReader;
import edu.upenn.cit594.processor.Processor;

import java.io.IOException;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws ParseException, org.json.simple.parser.ParseException, IOException {

        ZipcodeReader zipcodeReader = new ZipcodeReader("population.txt");
        Processor processor = new Processor(zipcodeReader);

        processor.displayTotalPopAllZipCodes();

    }
}
