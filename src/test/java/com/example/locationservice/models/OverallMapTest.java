package com.example.locationservice.models;

import com.example.locationservice.dtos.UserLocationDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ContextConfiguration(classes = {OverallMap.class})
class OverallMapTest {

    @Autowired
    private OverallMap testee;

    @BeforeEach
    void setUp() {

        Map<Integer, String> testLocations = new HashMap<>();
        testLocations.put(1, "user-1-test-location");
        testLocations.put(2, "user-2-test-location");
        testLocations.put(3, "user-3-test-location");
        testLocations.put(4, "user-4-test-location");
        testLocations.put(5, "user-5-test-location");

        this.testee = new OverallMap();
        testee.setId(99999);
        testee.setUserLocations(testLocations);
    }

    @AfterEach
    void tearDown() {
        this.testee = null;
    }

    @Test
    void getAllUserLocations() {
        Map<Integer, String> expectedUserLocations = new HashMap<>();
        expectedUserLocations.put(1, "user-1-test-location");
        expectedUserLocations.put(2, "user-2-test-location");
        expectedUserLocations.put(3, "user-3-test-location");
        expectedUserLocations.put(4, "user-4-test-location");
        expectedUserLocations.put(5, "user-5-test-location");

        assertNotNull(testee);
        assertNotNull(testee.getAllUserLocations());
        assertEquals(expectedUserLocations, testee.getAllUserLocations());
    }

    @Test
    void getSingleUserLocation() {
        Map<Integer, String> expectedUserLocation = new HashMap<>();
        expectedUserLocation.put(2, "user-2-test-location");

        assertNotNull(testee);
        assertNotNull(testee.getAllUserLocations());
        assertNotNull(testee.getSingleUserLocation(2));
        assertEquals(expectedUserLocation, testee.getSingleUserLocation(2));
        assertEquals("user-2-test-location", testee.getSingleUserLocation(2).get(2));
    }

    @Test
    void addUserLocation() {
        Map<Integer, String> expectedUserLocations = new HashMap<>();
        expectedUserLocations.put(1, "user-1-test-location");
        expectedUserLocations.put(2, "user-2-test-location");
        expectedUserLocations.put(3, "user-3-test-location");
        expectedUserLocations.put(4, "user-4-test-location");
        expectedUserLocations.put(5, "user-5-test-location");
        expectedUserLocations.put(6, "user-6-test-location");

        UserLocationDto newUserLocation = UserLocationDto.builder().uuid(6).location("user-6-test-location").build();
        testee.addUserLocation(newUserLocation);

        assertNotNull(testee);
        assertEquals(expectedUserLocations, testee.getAllUserLocations());
        assertEquals("user-6-test-location", testee.getSingleUserLocation(6).get(6));
    }

    @Test
    void updateUserLocation() {
        Map<Integer, String> expectedUserLocationss = new HashMap<>();
        expectedUserLocationss.put(1, "user-1-test-location");
        expectedUserLocationss.put(2, "user-2-test-location");
        expectedUserLocationss.put(3, "user-3-test-location");
        expectedUserLocationss.put(4, "user-4-updated-test-location");
        expectedUserLocationss.put(5, "user-5-test-location");

        UserLocationDto updatedUserLocation = UserLocationDto.builder().uuid(4).location("user-4-updated-test-location").build();
        testee.updateUserLocation(updatedUserLocation);

        assertNotNull(testee);
        assertEquals(expectedUserLocationss, testee.getAllUserLocations());
        assertEquals("user-4-updated-test-location", testee.getSingleUserLocation(4).get(4));
    }

    @Test
    void clearMap() {
        OverallMap expectedOverallMap = new OverallMap();
        expectedOverallMap.setId(99999);
        expectedOverallMap.setUserLocations(new HashMap<>());

        assertNotNull(testee.getAllUserLocations());
        testee.clearMap();

        assertEquals(expectedOverallMap, testee);
        assertEquals(99999, testee.getId());
        assertEquals(new HashMap<>(), testee.getAllUserLocations());
    }
}