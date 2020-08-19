package com.example.locationservice.services;


import com.example.locationservice.dtos.UserLocationDto;

import java.util.Map;

public interface OverallMapService {

    Map<Integer, String> getAllUserLocations();

    Map<Integer, String> getSingleUserLocation(int uuid);

    void addUserLocation(UserLocationDto userLocationDto);

    void updateUserLocation(UserLocationDto userLocationDto);
}
