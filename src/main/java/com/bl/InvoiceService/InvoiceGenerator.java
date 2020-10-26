package com.bl.InvoiceService;

import com.bl.Exception.InvoiceSummaryException;
import com.bl.RideDetails.Ride;
import com.bl.RideDetails.RideRepository;

import java.util.ArrayList;
import java.util.Arrays;

public class InvoiceGenerator {
    private static double MINIMUM_COST_PER_KILOMETER;
    private static double MINIMUM_COST_PER_MINUTE;
    private static double MINIMUM_FARE;
    private RideRepository rideRepository;

    public InvoiceGenerator(){
        rideRepository = new RideRepository();
    }
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
        return Math.max(totalFare,MINIMUM_FARE);
    }

    public double calculateFare(ArrayList<Ride> rides) {
        double totalFare = rides.stream().mapToDouble(ride -> calculateFare(ride.getDistance(), ride.getTime(), ride.getType())).sum();
        return totalFare;
    }

    public InvoiceSummary generateInvoiceSummary(ArrayList<Ride> rides) throws InvoiceSummaryException {
        int totalRides = rides.size();
        if (totalRides == 0 || rides == null)
            throw new InvoiceSummaryException("No rides", InvoiceSummaryException.ExceptionType.NO_RIDES);
        double totalFare = calculateFare(rides);
        return new InvoiceSummary(totalRides, totalFare);
    }

    public void addRides(String userId, ArrayList<Ride> rides) {
        rideRepository.addUsersRides(userId,rides);
    }

    public InvoiceSummary generateInvoiceSummary(String userId) throws InvoiceSummaryException {
        ArrayList<Ride> rides = rideRepository.getRideMap().get(userId);
        return generateInvoiceSummary(rides);
    }
}
