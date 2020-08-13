package edu.upenn.cit594.data;

public class Zipcode {
    protected int zipcode;
    protected int population;
    protected double totalFines;

    public Zipcode(int zipcode, int population) {
        this.zipcode = zipcode;
        this.population = population;
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

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public void setTotalFines(double totalFines) {
        this.totalFines = totalFines;
    }
}
