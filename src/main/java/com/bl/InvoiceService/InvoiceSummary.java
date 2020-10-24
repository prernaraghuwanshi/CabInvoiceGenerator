package com.bl.InvoiceService;

public class InvoiceSummary {
    private int totalRides;
    private double totalFare;
    private double averageFarePerRide;

    public InvoiceSummary(int totalRides, double totalFare) {
        this.totalFare = totalFare;
        this.totalRides = totalRides;
        this.averageFarePerRide = totalFare/totalRides;
    }

    public int getTotalRides() {
        return totalRides;
    }

    public void setTotalRides(int totalRides) {
        this.totalRides = totalRides;
    }

    public double getTotalFare() {
        return totalFare;
    }

    public void setTotalFare(double totalFare) {
        this.totalFare = totalFare;
    }

    public double getAverageFarePerRide() {
        return averageFarePerRide;
    }

    public void setAverageFarePerRide(double averageFarePerRide) {
        this.averageFarePerRide = averageFarePerRide;
    }
}
