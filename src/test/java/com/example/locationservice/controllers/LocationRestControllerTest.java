package com.example.locationservice.controllers;

import com.example.locationservice.dtos.UserLocationDto;
import com.example.locationservice.models.UserLocation;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class LocationRestControllerTest {

    private final Logger log = LoggerFactory.getLogger(LocationRestControllerTest.class);

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
    void addUserLocation_happy_flow() throws Exception {
        UserLocationDto newUserLocationDto = UserLocationDto.builder()
                .uuid(1)
                .location("my_location")
                .build();
        RequestBuilder request = MockMvcRequestBuilders.post("/add-user-location")
                .content(asJsonString(newUserLocationDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        assertTrue(overallMapController.getOverallMap().getUserLocations().isEmpty());
        mvc.perform(request).andReturn();
        assertFalse(overallMapController.getOverallMap().getUserLocations().isEmpty());
        assertEquals(1, overallMapController.getOverallMap().getUserLocations().size());
        assertEquals("OverallMap(id=0, userLocations=[UserLocation(uuid=1, location=my_location)])", overallMapController.getOverallMap().toString());
    }

    @Test
    void addUserLocation_unhappy_flow_null() throws Exception {
        UserLocationDto newUserLocationDto = null;
        RequestBuilder request = MockMvcRequestBuilders.post("/add-user-location")
                .content(asJsonString(newUserLocationDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        assertTrue(overallMapController.getOverallMap().getUserLocations().isEmpty());
        mvc.perform(request).andReturn();
        assertTrue(overallMapController.getOverallMap().getUserLocations().isEmpty());
    }

    @Test
    void getLocationForAddedUser() throws Exception {
        UserLocationDto newUserLocationDto = UserLocationDto.builder()
                .uuid(1)
                .location("my_location")
                .build();
        RequestBuilder addUserLocationRequest = MockMvcRequestBuilders.post("/add-user-location")
                .content(asJsonString(newUserLocationDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        RequestBuilder getUserLocationRequest = MockMvcRequestBuilders.get("/get-user-location?uuid=1");
        mvc.perform(addUserLocationRequest).andReturn();
        MvcResult resultGet = mvc.perform(getUserLocationRequest).andReturn();
        assertEquals("{\"uuid\":1,\"location\":\"my_location\"}", resultGet.getResponse().getContentAsString());
    }

    @Test
    void updateLocationForAddedUser() throws Exception {
        UserLocationDto oldUserLocation = UserLocationDto.builder()
                .uuid(1)
                .location("my_location")
                .build();
        UserLocationDto newUserLocationDto = UserLocationDto.builder()
                .uuid(1)
                .location("my_new_location")
                .build();
        RequestBuilder addUserLocationRequest = MockMvcRequestBuilders.post("/add-user-location")
                .content(asJsonString(oldUserLocation))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        RequestBuilder updateUserLocationRequest = MockMvcRequestBuilders.post("/update-user-location")
                .content(asJsonString(newUserLocationDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        RequestBuilder getUserLocationRequest = MockMvcRequestBuilders.get("/get-user-location?uuid=1");
        mvc.perform(addUserLocationRequest).andReturn();
        mvc.perform(updateUserLocationRequest).andReturn();
        MvcResult resultGet = mvc.perform(getUserLocationRequest).andReturn();
        assertEquals("{\"uuid\":1,\"location\":\"my_new_location\"}", resultGet.getResponse().getContentAsString());
    }

    private static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}