package com.bl.InvoiceService;

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
        double totalFare = invoiceGenerator.calculateFare(distance, time);
        Assert.assertEquals(25, totalFare, 0.0);
    }

    @Test
    public void givenDistanceAndTime_shouldReturnMinimumFare() {
        double distance = 0.1;
        int time = 1;
        double totalFare = invoiceGenerator.calculateFare(distance, time);
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
}
