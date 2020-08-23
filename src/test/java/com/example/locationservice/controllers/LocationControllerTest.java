package com.example.locationservice.controllers;

import com.example.locationservice.services.OverallMapController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
class LocationControllerTest {

    private final Logger log = LoggerFactory.getLogger(LocationControllerTest.class);

    @Autowired
    private MockMvc mvc;

    @Autowired
    private OverallMapController overallMapController;

    @AfterEach
    void tearDown() {
        overallMapController.clearMap();
        log.info("Cleared Map after test run: {}", overallMapController.getOverallMap());
    }

    @Test
    void emptyOverallMap() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/overall-map");
        MvcResult result = mvc.perform(request).andReturn();
        assertEquals("{\"locations\":[]}", result.getResponse().getContentAsString());
    }

    @Test
    void addUserLocation() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/add-user-location?uuid=1&location=my_location");
        MvcResult result = mvc.perform(request).andReturn();
        assertEquals("{\"uuid\":1,\"location\":\"my_location\"}", result.getResponse().getContentAsString());
    }

    @Test
    void filledOverallMap() throws Exception {
        RequestBuilder addUserLocationRequest = MockMvcRequestBuilders.get("/add-user-location?uuid=1&location=my_location");
        RequestBuilder request = MockMvcRequestBuilders.get("/overall-map");
        MvcResult resultAdd = mvc.perform(addUserLocationRequest).andReturn();
        MvcResult result = mvc.perform(request).andReturn();
        assertEquals("{\"uuid\":1,\"location\":\"my_location\"}", resultAdd.getResponse().getContentAsString());
        assertEquals("{\"locations\":[{\"uuid\":1,\"location\":\"my_location\"}]}", result.getResponse().getContentAsString());
    }

    @Test
    void getLocationForAddedUser() throws Exception {
        RequestBuilder addUserLocationRequest = MockMvcRequestBuilders.get("/add-user-location?uuid=1&location=my_location");
        RequestBuilder getUserLocationRequest = MockMvcRequestBuilders.get("/get-user-location?uuid=1");
        MvcResult resultAdd = mvc.perform(addUserLocationRequest).andReturn();
        MvcResult resultGet = mvc.perform(getUserLocationRequest).andReturn();
        assertEquals("{\"uuid\":1,\"location\":\"my_location\"}", resultAdd.getResponse().getContentAsString());
        assertEquals("{\"uuid\":1,\"location\":\"my_location\"}", resultGet.getResponse().getContentAsString());
    }

    @Test
    void updateLocationForAddedUser() throws Exception {
        RequestBuilder addUserLocationRequest = MockMvcRequestBuilders.get("/add-user-location?uuid=1&location=my_location");
        RequestBuilder updateUserLocationRequest = MockMvcRequestBuilders.get("/update-user-location?uuid=1&location=my_new_location");
        RequestBuilder getUserLocationRequest = MockMvcRequestBuilders.get("/get-user-location?uuid=1");
        MvcResult resultAdd = mvc.perform(addUserLocationRequest).andReturn();
        MvcResult resultUpdate = mvc.perform(updateUserLocationRequest).andReturn();
        MvcResult resultGet = mvc.perform(getUserLocationRequest).andReturn();
        assertEquals("{\"uuid\":1,\"location\":\"my_location\"}", resultAdd.getResponse().getContentAsString());
        assertEquals("{\"uuid\":1,\"location\":\"my_new_location\"}", resultUpdate.getResponse().getContentAsString());
        assertEquals("{\"uuid\":1,\"location\":\"my_new_location\"}", resultGet.getResponse().getContentAsString());
    }

}