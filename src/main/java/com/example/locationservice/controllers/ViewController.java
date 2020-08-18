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

    @GetMapping(value = "/")
    public ModelAndView showLocationServicePage() {
        ModelAndView locationServicePage = new ModelAndView("start_page");
        locationServicePage.addObject("overallMapDto", locationService.getOverallMapDto());
        return locationServicePage;
    }

    @GetMapping(value = "/update")
    public ModelAndView showLocationServicePageWithMap(@RequestParam(value = "uuid") int uuid, @RequestParam(value = "address") String location) {
        ModelAndView locationServicePage = new ModelAndView("start_page");
        locationServicePage.addObject("overallMapDto", locationService.updateUserLocation(uuid, location));
        return locationServicePage;
    }
}
