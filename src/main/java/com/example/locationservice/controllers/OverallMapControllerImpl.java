package com.example.locationservice.controllers;

import com.example.locationservice.models.OverallMap;
import com.example.locationservice.models.UserLocation;
import com.example.locationservice.services.OverallMapController;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Component
public class OverallMapControllerImpl implements OverallMapController {

    private OverallMap overallMap;

    @PostConstruct
    public void init() {
        this.overallMap = new OverallMap( new ArrayList<>());
    }

    @Override
    public OverallMap getOverallMap() {
        return this.overallMap;
    }

    @Override
    public UserLocation getSingleUserLocation(int uuid) {
        List<UserLocation> currentLocations = new ArrayList<>(this.overallMap.getUserLocations());
        return currentLocations.stream()
                .filter(userLocation -> userLocation.getUuid() == uuid)
                .findAny()
                .orElse(null);
    }

    @Override
    public void addUserLocation(UserLocation userLocation) {
        List<UserLocation> currentLocations = new ArrayList<>(this.overallMap.getUserLocations());
        currentLocations.add(userLocation);
        this.overallMap.setUserLocations(currentLocations);
    }

    @Override
    public void updateUserLocation(UserLocation userLocation) {
        List<UserLocation> currentLocations = new ArrayList<>(this.overallMap.getUserLocations());
        boolean isReplaced = Collections.replaceAll(currentLocations, getSingleUserLocation(userLocation.getUuid()), userLocation);
        if(isReplaced) {
            this.overallMap.setUserLocations(currentLocations);
        }
    }

    @Override
    public void clearMap() {
        this.overallMap.setUserLocations(new ArrayList<>());
    }
}
