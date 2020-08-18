package com.example.locationservice.controllers;

import com.example.locationservice.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewController {

    @Autowired
    private LocationService locationService;

    @GetMapping(value = "/location-service")
    public ModelAndView showLocationServicePage(@RequestParam(required = false) int uuid) {
        ModelAndView locationServicePage = new ModelAndView("location_service_page");
        locationServicePage.addObject("dto", locationService.getOverallMapDto());
        return locationServicePage;
    }
}
