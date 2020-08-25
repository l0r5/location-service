package com.example.locationservice.controllers;

import com.example.locationservice.models.OverallMap;
import com.example.locationservice.models.UserLocation;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Component
public class OverallMapController {

    private OverallMap overallMap;

    @PostConstruct
    public void init() {
        this.overallMap = new OverallMap( new ArrayList<>());
    }

    public OverallMap getOverallMap() {
        return this.overallMap;
    }

    public UserLocation getSingleUserLocation(int uuid) {
        List<UserLocation> currentLocations = new ArrayList<>(this.overallMap.getUserLocations());
        UserLocation resultLocation = currentLocations.stream()
                .filter(userLocation -> userLocation.getUuid() == uuid)
                .findAny()
                .orElse(null);
        if(resultLocation == null) {
            resultLocation = UserLocation.builder().uuid(uuid).location("No location found.").build();
        }
        return resultLocation;
    }

    public void addUserLocation(UserLocation userLocation) {
        List<UserLocation> currentLocations = new ArrayList<>(this.overallMap.getUserLocations());
        currentLocations.add(userLocation);
        this.overallMap.setUserLocations(currentLocations);
    }

    public void updateUserLocation(UserLocation userLocation) {
        List<UserLocation> currentLocations = new ArrayList<>(this.overallMap.getUserLocations());
        boolean isReplaced = Collections.replaceAll(currentLocations, getSingleUserLocation(userLocation.getUuid()), userLocation);
        if(isReplaced) {
            this.overallMap.setUserLocations(currentLocations);
        }
    }

    public void clearMap() {
        this.overallMap.setUserLocations(new ArrayList<>());
    }

    public void createNewOverallMapWithUserLocations(List<UserLocation> currentUserLocations) {
        this.overallMap = new OverallMap(currentUserLocations);
    }
}
