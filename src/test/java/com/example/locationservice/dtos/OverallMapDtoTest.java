package com.example.locationservice.dtos;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OverallMapDtoTest {

    OverallMapDto testee;

    @BeforeEach
    void setUp() {
        Map<Integer, String> testLocation = new HashMap<>();
        testLocation.put(1, "test-location-1");
        testLocation.put(9999, "test-location-2");
        testee = OverallMapDto.builder().locations(testLocation).build();
    }

    @AfterEach
    void tearDown() {
        testee = null;
    }

    @Test
    void testGetLocations() {
        Map<Integer, String> expectedLocations = new HashMap<>();
        expectedLocations.put(1, "test-location-1");
        expectedLocations.put(9999, "test-location-2");
        assertNotNull(testee);
        assertEquals(expectedLocations, testee.getLocations());
        assertEquals("test-location-2", testee.getLocations().get(9999));
    }

    @Test
    void setLocations() {
        Map<Integer, String> expectedLocations = new HashMap<>();
        expectedLocations.put(1, "test-location-1");
        expectedLocations.put(9999, "test-location-1");
        assertNotNull(testee);
        testee.setLocations(expectedLocations);
        assertEquals(expectedLocations, testee.getLocations());
        assertEquals("test-location-1", testee.getLocations().get(9999));
    }
}