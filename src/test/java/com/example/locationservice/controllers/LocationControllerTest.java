package com.example.locationservice.controllers;

import com.example.locationservice.models.OverallMap;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class LocationControllerTest {


    @Autowired
    private MockMvc mvc;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void emptyOverallMap_test() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/overall-map");
        MvcResult result = mvc.perform(request).andReturn();
        assertEquals("{\"locations\":{}}", result.getResponse().getContentAsString());
    }

    @Test
    void addUserLocation_test() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/add-user-location?uuid=1&location=my_location");
        MvcResult result = mvc.perform(request).andReturn();
        assertEquals("{\"uuid\":1,\"location\":\"my_location\"}", result.getResponse().getContentAsString());
    }

    @Test
    void filledOverallMap_test() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/overall-map");
        MvcResult result = mvc.perform(request).andReturn();
        assertEquals("{\"locations\":{\"1\":\"my_location\"}}", result.getResponse().getContentAsString());
    }

    @Test
    void getLocationForAddedUser_test() throws Exception {
        RequestBuilder addUserLocationRequest = MockMvcRequestBuilders.get("/add-user-location?uuid=1&location=my_location");
        RequestBuilder getUserLocationRequest = MockMvcRequestBuilders.get("/get-user-location?uuid=1");
        MvcResult resultAdd = mvc.perform(addUserLocationRequest).andReturn();
        MvcResult resultGet = mvc.perform(getUserLocationRequest).andReturn();
        assertEquals("{\"uuid\":1,\"location\":\"my_location\"}", resultAdd.getResponse().getContentAsString());
        assertEquals("{\"uuid\":1,\"location\":\"my_location\"}", resultGet.getResponse().getContentAsString());
    }

}