package com.bl.InvoiceService;

import com.bl.Exception.InvoiceSummaryException;
import com.bl.RideDetails.Ride;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class InvoiceServiceTest {

    InvoiceGenerator invoiceGenerator = null;
    private ArrayList<Ride> rides = new ArrayList<>();
    private ArrayList<Ride> ridePremium = new ArrayList<>();

    @Before
    public void initialize() {
        invoiceGenerator = new InvoiceGenerator();
        rides.add(new Ride(2.0, 5));
        rides.add(new Ride(0.1, 1));
        ridePremium.add(new Ride(1, 1, "premium"));
        ridePremium.add(new Ride(2, 5, "premium"));
    }

    @Test
    public void givenDistanceAndTime_shouldReturnTotalFare() {
        double distance = 2.0;
        int time = 5;
        Ride ride = new Ride(distance, time);
        double totalFare = invoiceGenerator.calculateFare(distance, time, ride.getType());
        Assert.assertEquals(25, totalFare, 0.0);
    }

    @Test
    public void givenDistanceAndTime_shouldReturnMinimumFare() {
        double distance = 0.1;
        int time = 1;
        Ride ride = new Ride(distance, time);
        double totalFare = invoiceGenerator.calculateFare(distance, time, ride.getType());
        Assert.assertEquals(5, totalFare, 0.0);
    }

    @Test
    public void givenMultipleRides_shouldReturnTotalFare() {
        double totalFare = invoiceGenerator.calculateFare(rides);
        Assert.assertEquals(30, totalFare, 0.0);
    }

    @Test
    public void givenMultipleRides_shouldReturnInvoiceSummary() {
        try {
            InvoiceSummary invoiceSummary = invoiceGenerator.generateInvoiceSummary(rides);
            InvoiceSummary expectedSummary = new InvoiceSummary(2, 30);
            Assert.assertEquals(2, invoiceSummary.getTotalRides());
            Assert.assertEquals(30, invoiceSummary.getTotalFare(), 0.0);
            Assert.assertEquals(15, invoiceSummary.getAverageFarePerRide(), 0.0);
            Assert.assertEquals(expectedSummary, invoiceSummary);
        } catch (InvoiceSummaryException e) {
        }
    }

    @Test
    public void givenNoRides_shouldThrowInvoiceSummaryException() {
        ArrayList<Ride> noRides = new ArrayList<>();
        try {
            InvoiceSummary invoiceSummary = invoiceGenerator.generateInvoiceSummary(noRides);
        } catch (InvoiceSummaryException e) {
            Assert.assertEquals(InvoiceSummaryException.ExceptionType.NO_RIDES, e.type);
        }
    }

    @Test
    public void givenUserId_shouldReturnInvoiceSummary() {
        String userId = "prerna4498";
        invoiceGenerator.addRides(userId, rides);
        try {
            InvoiceSummary invoiceSummary = invoiceGenerator.generateInvoiceSummary(userId);
            InvoiceSummary expectedSummary = new InvoiceSummary(2, 30);
            Assert.assertEquals(2, invoiceSummary.getTotalRides());
            Assert.assertEquals(30, invoiceSummary.getTotalFare(), 0.0);
            Assert.assertEquals(15, invoiceSummary.getAverageFarePerRide(), 0.0);
            Assert.assertEquals(expectedSummary, invoiceSummary);
        } catch (InvoiceSummaryException e) {
        }
    }

    @Test
    public void givenPremiumRide_shouldReturnInvoiceSummary() {
        try {
            InvoiceSummary invoiceSummary = invoiceGenerator.generateInvoiceSummary(ridePremium);
            InvoiceSummary expectedSummary = new InvoiceSummary(2, 60);
            Assert.assertEquals(2, invoiceSummary.getTotalRides());
            Assert.assertEquals(60, invoiceSummary.getTotalFare(), 0.0);
            Assert.assertEquals(30, invoiceSummary.getAverageFarePerRide(), 0.0);
            Assert.assertEquals(expectedSummary, invoiceSummary);
        } catch (InvoiceSummaryException e) {
        }
    }
    @Test
    public void givenExistingUserId_shouldAppendNewRidesAndReturnInvoiceSummary() {
        String userId = "prerna4498";
        ArrayList<Ride> newRides = new ArrayList<>();
        newRides.add(new Ride(2.0,5));
        newRides.add(new Ride(0.1,1));
        invoiceGenerator.addRides(userId, rides);
        invoiceGenerator.addRides(userId, newRides);
        try {
            InvoiceSummary invoiceSummary = invoiceGenerator.generateInvoiceSummary(userId);
            InvoiceSummary expectedSummary = new InvoiceSummary(4, 60);
            Assert.assertEquals(4, invoiceSummary.getTotalRides());
            Assert.assertEquals(60, invoiceSummary.getTotalFare(), 0.0);
            Assert.assertEquals(15, invoiceSummary.getAverageFarePerRide(), 0.0);
            Assert.assertEquals(expectedSummary, invoiceSummary);
        } catch (InvoiceSummaryException e) {
        }
    }
}
