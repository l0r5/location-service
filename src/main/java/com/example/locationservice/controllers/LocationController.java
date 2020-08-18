package com.example.locationservice.controllers;

import com.example.locationservice.dtos.UserLocationDto;
import com.example.locationservice.services.LocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocationController {

    private final Logger log = LoggerFactory.getLogger(LocationController.class);

    @Autowired
    private LocationService locationService;

    @GetMapping("/overall-map")
    public String overallMap() {
        log.info("GET /overall-map triggered.");
        return locationService.getOverallMapDto().toString();
    }

    @GetMapping("/add-user-location")
    public UserLocationDto addUserLocation(@RequestParam(value = "uuid") int uuid) {
        log.info("GET /add-user-location?uuid={} triggered.", uuid);
        locationService.addUserLocation(uuid, "Address");
        return locationService.getUserLocation(uuid);
    }

    @GetMapping("/get-user-location")
    public UserLocationDto getUserLocation(@RequestParam(value = "uuid") int uuid) {
        log.info("GET /get-user-location?uuid={} triggered.", uuid);
        return locationService.getUserLocation(uuid);
    }

    @GetMapping("/update-user-location")
    public String updateUserLocation(@RequestParam(value = "uuid") int uuid, @RequestParam(value = "address") String address) {
        log.info("GET /update-user-location?uuid={}&address={} triggered.", uuid, address);
        return locationService.updateUserLocation(uuid, address).toString();
    }
}
