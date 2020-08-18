package edu.upenn.cit594.processor;

import edu.upenn.cit594.data.Property;
import edu.upenn.cit594.data.Zipcode;

import java.util.HashMap;

public class AverageMarketValue implements AverageByZip{

    public int displayAverage(HashMap<Integer, Property> properties, int zipCode) {

        Property masterProperty = new Property(0, 0, zipCode);
        double propertyCount = 0;

        for (Property property : properties.values()) {

            try {
                int propertyZip = property.getZipcode();
                if (propertyZip == zipCode) {
                    masterProperty.setMarketValue(masterProperty.getMarketValue() + property.getMarketValue());
                    propertyCount++;
                }
            } catch (NullPointerException ignored) {
            }
        }

        return (int) Math.floor(masterProperty.getMarketValue() / propertyCount);

    }

}
