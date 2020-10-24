package com.bl.InvoiceService;

import com.bl.Exception.InvoiceSummaryException;

import java.util.Arrays;

public class InvoiceGenerator {

    private static final double MINIMUM_COST_PER_KILOMETER = 10;
    private static final double MINIMUM_COST_PER_MINUTE = 1;
    private static final double MINIMUM_FARE = 5;
    private RideRepository rideRepository = new RideRepository();

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

    public InvoiceSummary generateInvoiceSummary(Ride[] rides) throws InvoiceSummaryException {
        int totalRides = rides.length;
        if (totalRides == 0 || rides == null)
            throw new InvoiceSummaryException("No rides", InvoiceSummaryException.ExceptionType.NO_RIDES);
        double totalFare = calculateFare(rides);
        return new InvoiceSummary(totalRides, totalFare);
    }

    public RideRepository getRideDetails() {
        return rideRepository;
    }

    public InvoiceSummary generateInvoiceSummary(String userId) throws InvoiceSummaryException {
        Ride[] rides = rideRepository.getRideMap().get(userId);
        return generateInvoiceSummary(rides);
    }
}
