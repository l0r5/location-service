package com.example.locationservice.services;


import com.example.locationservice.models.OverallMap;
import com.example.locationservice.models.UserLocation;

public interface OverallMapController {

    OverallMap getOverallMap();

    UserLocation getSingleUserLocation(int uuid);

    void addUserLocation(UserLocation userLocation);

    void updateUserLocation(UserLocation userLocation);

    void clearMap();
}
