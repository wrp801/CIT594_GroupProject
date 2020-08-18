package edu.upenn.cit594.processor;

import edu.upenn.cit594.data.Property;
import edu.upenn.cit594.data.Zipcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface AverageByZip {

    // DUMMY values
    List<Property> properties = new ArrayList<>();

    public int displayAverage(HashMap<Integer, Property> properties, int zipCode);

}