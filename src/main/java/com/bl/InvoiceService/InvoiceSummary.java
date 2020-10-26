package com.bl.InvoiceService;

import java.util.Objects;

public class InvoiceSummary {
    private int totalRides;
    private double totalFare;
    private double averageFarePerRide;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceSummary that = (InvoiceSummary) o;
        return totalRides == that.totalRides &&
                Double.compare(that.totalFare, totalFare) == 0 &&
                Double.compare(that.averageFarePerRide, averageFarePerRide) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalRides, totalFare, averageFarePerRide);
    }

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
