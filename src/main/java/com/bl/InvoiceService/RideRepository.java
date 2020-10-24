package com.bl.InvoiceService;

import java.util.HashMap;

public class RideRepository {
    private HashMap<String, Ride[]> rideMap = new HashMap<>();

    public void addUsersRides(String userId, Ride[] rides) {
        rideMap.put(userId,rides);
    }

    public HashMap<String ,Ride[]> getRideMap()
    {
        return rideMap;
    }
}
