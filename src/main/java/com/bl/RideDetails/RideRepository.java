package com.bl.RideDetails;

import com.bl.RideDetails.Ride;

import java.util.ArrayList;
import java.util.HashMap;

public class RideRepository {
    private HashMap<String, ArrayList<Ride>> rideMap;

    public RideRepository(){
        rideMap = new HashMap<>();
    }

    public void addUsersRides(String userId, ArrayList<Ride> rides) {
        if (rideMap.get(userId) == null)
            rideMap.put(userId, rides);
        else {
            ArrayList<Ride> userRidesList;
            userRidesList = rideMap.get(userId);
            userRidesList.addAll(rides);
            rideMap.replace(userId,userRidesList);
        }
    }

    public HashMap<String, ArrayList<Ride>> getRideMap() {
        return rideMap;
    }
}
