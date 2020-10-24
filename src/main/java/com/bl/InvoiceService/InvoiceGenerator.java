package com.bl.InvoiceService;

import com.bl.Exception.InvoiceSummaryException;

import java.util.Arrays;

public class InvoiceGenerator {
    private static double MINIMUM_COST_PER_KILOMETER;
    private static double MINIMUM_COST_PER_MINUTE;
    private static double MINIMUM_FARE;
    private RideRepository rideRepository = new RideRepository();

    public void welcomeMessage() {
        System.out.println("Welcome to Cab Invoice Generator!");
    }

    private void setInvoiceConstants(String rideType) {
        if (rideType.equalsIgnoreCase("normal")) {
            MINIMUM_COST_PER_KILOMETER = 10;
            MINIMUM_COST_PER_MINUTE = 1;
            MINIMUM_FARE = 5;
        } else if (rideType.equalsIgnoreCase("premium")) {
            MINIMUM_COST_PER_KILOMETER = 15;
            MINIMUM_COST_PER_MINUTE = 2;
            MINIMUM_FARE = 20;
        }
    }

    public double calculateFare(double distance, int time, String rideType) {
        setInvoiceConstants(rideType);
        double totalFare = MINIMUM_COST_PER_KILOMETER * distance + MINIMUM_COST_PER_MINUTE * time;
        if (totalFare < MINIMUM_FARE)
            totalFare = MINIMUM_FARE;
        return totalFare;
    }

    public double calculateFare(Ride[] rides) {
        double totalFare = Arrays.stream(rides).mapToDouble(ride -> calculateFare(ride.getDistance(), ride.getTime(), ride.getType())).sum();
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
