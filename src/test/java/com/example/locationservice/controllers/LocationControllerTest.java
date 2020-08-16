package com.example.locationservice.controllers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(LocationController.class)
class LocationControllerIntegrationTest {


   @Autowired
   private MockMvc mvc;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void location() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/location?uuid=1");
        MvcResult result = mvc.perform(request).andReturn();
        assertEquals("{\"id\":1,\"uuid\":1,\"location\":\"123\"}", result.getResponse().getContentAsString());
    }
}