package com.bl.InvoiceService;

import java.util.Arrays;

public class InvoiceGenerator {

    private static final double MINIMUM_COST_PER_KILOMETER = 10;
    private static final double MINIMUM_COST_PER_MINUTE = 1;
    private static final double MINIMUM_FARE = 5;

    public void welcomeMessage() {
        System.out.println("Welcome to Cab Invoice Generator!");
    }

    public double calculateFare(double distance, int time) {
        double totalFare = MINIMUM_COST_PER_KILOMETER * distance + MINIMUM_COST_PER_MINUTE * time;
        if (totalFare < MINIMUM_FARE)
            totalFare = MINIMUM_FARE;
        return totalFare;
    }

    public double calculateFare(Ride[] rides) {
        double totalFare = Arrays.stream(rides).mapToDouble(ride -> calculateFare(ride.getDistance(), ride.getTime())).sum();
        return totalFare;
    }
}
