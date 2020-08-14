package edu.upenn.cit594.data;

public class Zipcode {
    protected int zipcode;
    protected int population;
    protected double totalFines;        // placing it here in Data layer may be bad design - revisit
    protected double totalMarketValue;  // placing it here in Data layer may be bad design - revisit
    protected double totalLivableArea;  // placing it here in Data layer may be bad design - revisit
    protected int numHouses;  // placing it here in Data layer may be bad design - revisit

    public Zipcode(int zipcode, int population) {
        this.zipcode = zipcode;
        this.population = population;
        numHouses = 0;
    }

    public void incrementHouses() {
        numHouses ++ ;
    }

    public int getZipcode() {
        return zipcode;
    }

    public int getPopulation() {
        return population;
    }

    public double getTotalFines() {
        return totalFines;
    }

    public double getTotalMarketValue() {
        return totalMarketValue;
    }

    public int getNumHouses() {
        return numHouses;
    }

    public double getTotalLivableArea() {
        return totalLivableArea;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public void setTotalFines(double totalFines) {
        this.totalFines = totalFines;
    }

    public void setTotalMarketValue(double totalMarketValue) {
        this.totalMarketValue = totalMarketValue;
    }

    public void setTotalLivableArea(double totalLivableArea) {
        this.totalLivableArea = totalLivableArea;
    }
}
