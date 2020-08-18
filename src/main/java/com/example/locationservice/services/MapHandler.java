package com.example.locationservice.services;


import com.example.locationservice.dtos.UserLocationDto;

import java.util.Map;

public interface MapHandler {
    public Map<Integer, String> getUserLocations();
    public void addUserLocation(UserLocationDto userLocationDto);
    public void updateUserLocation(UserLocationDto userLocationDto);
}
