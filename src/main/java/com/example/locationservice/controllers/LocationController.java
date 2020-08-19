package com.example.locationservice.controllers;

import com.example.locationservice.dtos.OverallMapDto;
import com.example.locationservice.dtos.UserLocationDto;
import com.example.locationservice.services.LocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocationController {

    private final Logger log = LoggerFactory.getLogger(LocationController.class);

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/overall-map")
    public OverallMapDto overallMap() {
        log.info("GET /overall-map triggered.");
        return locationService.getOverallMapDto();
    }

    @GetMapping("/add-user-location")
    public UserLocationDto addUserLocation(@RequestParam(value = "uuid") int uuid, @RequestParam(value = "location") String location) {
        log.info("GET /add-user-location?uuid={} triggered.", uuid);
        locationService.addUserLocation(uuid, location);
        return locationService.getUserLocation(uuid);
    }

    @GetMapping("/get-user-location")
    public UserLocationDto getUserLocation(@RequestParam(value = "uuid") int uuid) {
        log.info("GET /get-user-location?uuid={} triggered.", uuid);
        return locationService.getUserLocation(uuid);
    }

    @GetMapping("/update-user-location")
    public UserLocationDto updateUserLocation(@RequestParam(value = "uuid") int uuid, @RequestParam(value = "location") String location) {
        log.info("GET /update-user-location?uuid={}&address={} triggered.", uuid, location);
        return locationService.updateUserLocation(uuid, location);
    }
}
