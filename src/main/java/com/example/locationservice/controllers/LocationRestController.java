package com.example.locationservice.controllers;

import com.example.locationservice.dtos.OverallMapDto;
import com.example.locationservice.dtos.UserLocationDto;
import com.example.locationservice.services.LocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@RestController
public class LocationRestController {

    private final Logger log = LoggerFactory.getLogger(LocationRestController.class);

    private final LocationService locationService;

    public LocationRestController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/overall-map")
    @ResponseBody
    public OverallMapDto overallMap() {
        log.info("GET /overall-map triggered.");
        return locationService.getOverallMapDto();
    }

    @PostMapping("/add-user-location")
    @ResponseBody
    public UserLocationDto addUserLocation(@RequestBody UserLocationDto userLocationDto) {
        log.info("POST /add-user-location - Received: {}", userLocationDto.toString());
        locationService.addUserLocation(userLocationDto);
        return locationService.getUserLocation(userLocationDto.getUuid());
    }

    @GetMapping("/get-user-location")
    @ResponseBody
    public UserLocationDto getUserLocation(@RequestParam(value = "uuid") int uuid) {
        log.info("GET /get-user-location?uuid={}", uuid);
        return locationService.getUserLocation(uuid);
    }

    @PostMapping("/update-user-location")
    @ResponseBody
    public UserLocationDto updateUserLocation(@RequestBody UserLocationDto userLocationDto) {
        log.info("POST /update-user-location - Received: {}", userLocationDto.toString());
        return locationService.updateUserLocation(userLocationDto);
    }
}
