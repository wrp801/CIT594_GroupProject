package edu.upenn.cit594.data;

import java.util.Date;

public class Violation {
    protected String date;
    protected int fine;
    protected String description;
    protected int plateId;
    protected String state;
    protected int ticketNumber;
    protected int zipCode;

    public Violation(String date, int fine, String description, int plateId, String state, int ticketNumber, int zipCode) {
        this.date = date;
        this.fine = fine;
        this.description = description;
        this.plateId = plateId;
        this.state = state;
        this.ticketNumber = ticketNumber;
        this.zipCode = zipCode;
    }

    public String getDate() {
        return date;
    }

    public int getFine() {
        return fine;
    }

    public String getDescription() {
        return description;
    }

    public int getPlateId() {
        return plateId;
    }

    public String getState() {
        return state;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public int getZipCode() {
        return zipCode;
    }
}
