package com.bl.InvoiceService;

public class Ride {
    private double distance;
    private int time;
    private String type;

    public Ride(double distance, int time) {
        this.distance = distance;
        this.time = time;
        this.type = "normal";
    }

    public Ride(double distance, int time, String type) {
        this.distance = distance;
        this.time = time;
        this.type = type;
    }

    public double getDistance() {
        return distance;
    }

    public int getTime() {
        return time;
    }

    public String getType() {
        return type;
    }
}
