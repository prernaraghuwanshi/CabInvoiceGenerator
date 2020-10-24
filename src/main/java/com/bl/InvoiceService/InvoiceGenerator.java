package com.bl.InvoiceService;

public class InvoiceGenerator {

    private static final double MINIMUM_COST_PER_KILOMETER = 10;
    private static final double MINIMUM_COST_PER_MINUTE = 1;

    public void welcomeMessage() {
        System.out.println("Welcome to Cab Invoice Generator!");
    }

    public double calculateFare(double distance, int time) {
        double fare = MINIMUM_COST_PER_KILOMETER * distance + MINIMUM_COST_PER_MINUTE * time;
        return fare;
    }
}
