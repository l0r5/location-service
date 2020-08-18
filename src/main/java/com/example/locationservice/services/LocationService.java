package com.example.locationservice.services;

import com.example.locationservice.dtos.UserLocationDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LocationService {

    private final Logger log = LoggerFactory.getLogger(LocationService.class);
    private final OverallMapService mapHandler;

    public LocationService(OverallMapService mapHandler) {
        this.mapHandler = mapHandler;
    }

    @EventListener(classes = ApplicationReadyEvent.class)
    public void init() {
        log.info("LocationService is ready. Application has been started.");
    }

    public Map<Integer, String> getOverallMapDto() {
        log.info("OverallMap: {}", mapHandler.getUserLocations().toString());
        return mapHandler.getUserLocations();
    }

    public void addUserLocation(int uuid, String location) {
        log.info("Add UserLocation for [uuid]: {}", uuid);
        UserLocationDto newUserLocation = UserLocationDto.builder().uuid(uuid).location(location).build();
        mapHandler.addUserLocation(newUserLocation);
        log.info("Added new UserLocation to the OverallMap: {}", mapHandler.getUserLocations().toString());
    }

    public UserLocationDto getUserLocation(int uuid) {
        log.info("Get Location for [uuid]: {}", uuid);
        String location = mapHandler.getUserLocations().get(uuid);
        log.info("Got [address]: {} for [uuid]: {}", location, uuid);
        return UserLocationDto.builder().uuid(uuid).location(location).build();
    }

    public Map<Integer, String> updateUserLocation(int uuid, String location) {
        log.info("Update Location for [uuid]: {}", uuid);
        UserLocationDto userLocationDto = UserLocationDto.builder().uuid(uuid).location(location).build();
        mapHandler.updateUserLocation(userLocationDto);
        log.info("User Location updated. Old [location]: {}; New [location]: {}", mapHandler.getUserLocations().get(uuid), location);
        return mapHandler.getUserLocations();
    }
}
