package com.example.locationservice.services;


import com.example.locationservice.dtos.UserLocationDto;

import java.util.Map;

public interface OverallMapService {

    Map<Integer, String> getUserLocations();

    void addUserLocation(UserLocationDto userLocationDto);

    void updateUserLocation(UserLocationDto userLocationDto);
}
