package com.bl.InvoiceService;

import com.bl.Exception.InvoiceSummaryException;
import com.bl.RideDetails.Ride;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InvoiceServiceTest {

    InvoiceGenerator invoiceGenerator = null;
    Ride[] rides = {
            new Ride(2.0, 5),
            new Ride(0.1, 1)
    };

    @Before
    public void initialize() {
        invoiceGenerator = new InvoiceGenerator();
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
            Assert.assertEquals(2, invoiceSummary.getTotalRides());
            Assert.assertEquals(30, invoiceSummary.getTotalFare(), 0.0);
            Assert.assertEquals(15, invoiceSummary.getAverageFarePerRide(), 0.0);
        } catch (InvoiceSummaryException e) {
        }
    }

    @Test
    public void givenNoRides_shouldThrowInvoiceSummaryException() {
        Ride[] noRides = {};
        try {
            InvoiceSummary invoiceSummary = invoiceGenerator.generateInvoiceSummary(noRides);
        } catch (InvoiceSummaryException e) {
            Assert.assertEquals(InvoiceSummaryException.ExceptionType.NO_RIDES, e.type);
        }
    }

    @Test
    public void givenUserId_shouldReturnInvoiceSummary() {
        String userId = "prerna4498";
        invoiceGenerator.getRideDetails().addUsersRides(userId, rides);
        try {
            InvoiceSummary invoiceSummary = invoiceGenerator.generateInvoiceSummary(userId);
            Assert.assertEquals(2, invoiceSummary.getTotalRides());
            Assert.assertEquals(30, invoiceSummary.getTotalFare(), 0.0);
            Assert.assertEquals(15, invoiceSummary.getAverageFarePerRide(), 0.0);
        } catch (InvoiceSummaryException e) {
        }
    }

    @Test
    public void givenPremiumRide_shouldReturnInvoiceSummary() {
        Ride[] ridePremium = {new Ride(1, 1, "premium"),
                new Ride(2, 5, "premium")};
        try {
            InvoiceSummary invoiceSummary =invoiceGenerator.generateInvoiceSummary(ridePremium);
            Assert.assertEquals(2, invoiceSummary.getTotalRides());
            Assert.assertEquals(60, invoiceSummary.getTotalFare(), 0.0);
            Assert.assertEquals(30, invoiceSummary.getAverageFarePerRide(), 0.0);
        } catch (InvoiceSummaryException e) {
        }

    }
}
