package com.example.locationservice.services;

import com.example.locationservice.dtos.OverallMapDto;
import com.example.locationservice.dtos.UserLocationDto;
import com.example.locationservice.models.OverallMap;
import com.example.locationservice.models.UserLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class LocationService {

    private final Logger log = LoggerFactory.getLogger(LocationService.class);
    private final OverallMapController mapService;

    public LocationService(OverallMapController map) {
        this.mapService = map;
    }

    @EventListener(classes = ApplicationReadyEvent.class)
    public void init() {
        log.info("LocationService is ready. Application has been started.");
    }

    public OverallMapDto getOverallMapDto() {
        log.info("Get current OverallMap");
        OverallMap overallMap = mapService.getOverallMap();
        log.info("OverallMap: {}", overallMap.toString());
        return OverallMapDto.from(overallMap);
    }

    public void addUserLocation(int uuid, String location) {
        log.info("Add UserLocation for [uuid]: {}", uuid);
        UserLocation newUserLocation = UserLocation.builder().uuid(uuid).location(location).build();
        mapService.addUserLocation(newUserLocation);
        log.info("Added new UserLocation to the OverallMap: {}", mapService.getOverallMap().toString());
    }

    public UserLocationDto getUserLocation(int uuid) {
        log.info("Get Location for [uuid]: {}", uuid);
        UserLocation userLocation = mapService.getSingleUserLocation(uuid);
        log.info("Got [address]: {} for [uuid]: {}", userLocation.getLocation(), uuid);
        return UserLocationDto.from(userLocation);
    }

    public UserLocationDto updateUserLocation(int uuid, String location) {
        log.info("Update Location for [uuid]: {}", uuid);
        UserLocationDto userLocationDto = UserLocationDto.builder().uuid(uuid).location(location).build();
        mapService.updateUserLocation(userLocationDto.toUserLocation());
        log.info("User Location updated. Old [location]: {}; New [location]: {}", mapService.getSingleUserLocation(uuid), location);
        UserLocation userLocation = mapService.getSingleUserLocation(uuid);
        log.info("Got [address]: {} for [uuid]: {}", userLocation, uuid);
        return UserLocationDto.from(userLocation);
    }
}
