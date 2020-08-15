package edu.upenn.cit594.data;

public class Property {
    protected double marketValue;
    protected double totalLiveableArea;
    protected int zipcode;
    protected String buildingcode;

    public Property(double marketValue, double totalLiveableArea, int zipcode) { // there's a less verbose way to overload
        this.marketValue = marketValue;
        this.totalLiveableArea = totalLiveableArea;
        this.zipcode = zipcode;
    }

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

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public void setMarketValue(double marketValue) {
        this.marketValue = marketValue;
    }

    public void setTotalLiveableArea(double totalLiveableArea) {
        this.totalLiveableArea = totalLiveableArea;
    }

    public String getBuildingcode() {
        return buildingcode;

    }
}
