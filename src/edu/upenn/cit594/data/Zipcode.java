package edu.upenn.cit594.data;

public class Zipcode {
    protected int zipcode;
    protected int population;

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
}
