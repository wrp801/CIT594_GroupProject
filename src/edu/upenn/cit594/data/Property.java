package edu.upenn.cit594.data;

public class Property {
    protected double marketValue;
    protected double totalLiveableArea;
    protected int zipcode;
    protected String buildingcode;

    public Property(double marketValue, double totalLiveableArea, int zipcode, String buildingcode) {
        this.marketValue = marketValue;
        this.totalLiveableArea = totalLiveableArea;
        this.zipcode = zipcode;
        this.buildingcode = buildingcode;
    }

    public double getMarketValue() {
        return marketValue;
    }

    public double getTotalLiveableArea() {
        return totalLiveableArea;
    }

    public int getZipcode() {
        return zipcode;
    }

    public String getBuildingcode() {
        return buildingcode;
    }
}
