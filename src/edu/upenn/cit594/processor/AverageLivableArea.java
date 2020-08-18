package edu.upenn.cit594.processor;

import edu.upenn.cit594.data.Property;
import edu.upenn.cit594.data.Zipcode;

import java.util.HashMap;

public class AverageLivableArea implements AverageByZip{

    public void displayAverage(HashMap<Integer, Property> properties, int zipCode) {

        Property masterProperty = new Property(0, 0, zipCode);
        double propertyCount = 0;

        for (Property property : properties.values()) {

            try {
                int propertyZip = property.getZipcode();
                if (propertyZip == zipCode) {
                    masterProperty.setTotalLiveableArea(masterProperty.getTotalLiveableArea() + property.getTotalLiveableArea());
                    propertyCount++;
                }
            } catch (NullPointerException ignored) {
            }
        }

        int avgLivableArea = (int) Math.floor(masterProperty.getTotalLiveableArea() / propertyCount);
        System.out.println(avgLivableArea);

    }

}
