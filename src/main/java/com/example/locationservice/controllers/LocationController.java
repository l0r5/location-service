package com.example.locationservice.controllers;

import com.example.locationservice.Location;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class LocationController {

    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/location")
    public Location location(@RequestParam(value = "uuid") int uuid) {
        return new Location(counter.incrementAndGet(), uuid, "123");
    }

}
