package edu.upenn.cit594.data;

public class Property {
    protected int marketValue;
    protected int totalLiveableArea;
    protected int zipcode;

    public Property(int marketValue, int totalLiveableArea, int zipcode) {
        this.marketValue = marketValue;
        this.totalLiveableArea = totalLiveableArea;
        this.zipcode = zipcode;
    }

    public int getMarketValue() {
        return marketValue;
    }

    public int getTotalLiveableArea() {
        return totalLiveableArea;
    }

    public int getZipcode() {
        return zipcode;
    }
}
