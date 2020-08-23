package com.example.locationservice.models;

import com.example.locationservice.controllers.OverallMapControllerImpl;
import com.example.locationservice.services.OverallMapController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = {OverallMapControllerImpl.class})
class OverallMapControllerTest {

    @Autowired
    private OverallMapController testee;

    @Autowired
    private OverallMapControllerImpl impl;

    private static final UserLocation testUserLocation1 = UserLocation.builder().uuid(1).location("user-1-test-location").build();
    private static final UserLocation testUserLocation2 = UserLocation.builder().uuid(2).location("user-2-test-location").build();
    private static final UserLocation testUserLocation3 = UserLocation.builder().uuid(3).location("user-3-test-location").build();
    private static final UserLocation testUserLocation4 = UserLocation.builder().uuid(4).location("user-4-test-location").build();
    private static final UserLocation testUserLocation5 = UserLocation.builder().uuid(5).location("user-5-test-location").build();


    @BeforeEach
    void setUp() {

        List<UserLocation> testLocations = new ArrayList<>();
        testLocations.add(testUserLocation1);
        testLocations.add(testUserLocation2);
        testLocations.add(testUserLocation3);
        testLocations.add(testUserLocation4);
        testLocations.add(testUserLocation5);

        impl.getOverallMap().setId(99999);
        impl.getOverallMap().setUserLocations(testLocations);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getOverallMap() {
        List<UserLocation> expectedUserLocations = new ArrayList<>();
        expectedUserLocations.add(testUserLocation1);
        expectedUserLocations.add(testUserLocation2);
        expectedUserLocations.add(testUserLocation3);
        expectedUserLocations.add(testUserLocation4);
        expectedUserLocations.add(testUserLocation5);

        OverallMap expectedMap = new OverallMap(99999, expectedUserLocations);

        assertNotNull(testee.getOverallMap());
        assertEquals(expectedMap, testee.getOverallMap());
        assertEquals(expectedMap.getUserLocations(), testee.getOverallMap().getUserLocations());
    }

    @Test
    void getSingleUserLocation() {
        UserLocation expectedUserLocation = UserLocation.builder().uuid(2).location("user-2-test-location").build();

        assertNotNull(testee);
        assertNotNull(testee.getOverallMap());
        assertNotNull(testee.getSingleUserLocation(2));
        assertEquals(expectedUserLocation, testee.getSingleUserLocation(2));
        assertEquals("user-2-test-location", testee.getSingleUserLocation(2).getLocation());
    }

    @Test
    void addUserLocation() {
        List<UserLocation> expectedUserLocations = new ArrayList<>();
        expectedUserLocations.add(testUserLocation1);
        expectedUserLocations.add(testUserLocation2);
        expectedUserLocations.add(testUserLocation3);
        expectedUserLocations.add(testUserLocation4);
        expectedUserLocations.add(testUserLocation5);
        expectedUserLocations.add(UserLocation.builder().uuid(6).location("user-6-test-location").build());

        UserLocation newUserLocation = UserLocation.builder().uuid(6).location("user-6-test-location").build();
        testee.addUserLocation(newUserLocation);

        assertNotNull(testee);
        assertEquals(expectedUserLocations, testee.getOverallMap().getUserLocations());
        assertEquals("user-6-test-location", testee.getSingleUserLocation(6).getLocation());
    }

    @Test
    void updateUserLocation() {
        List<UserLocation> expectedUserLocations = new ArrayList<>();
        expectedUserLocations.add(testUserLocation1);
        expectedUserLocations.add(testUserLocation2);
        expectedUserLocations.add(testUserLocation3);
        expectedUserLocations.add(UserLocation.builder().uuid(4).location("user-4-updated-test-location").build());
        expectedUserLocations.add(testUserLocation5);

        UserLocation updatedUserLocation = UserLocation.builder().uuid(4).location("user-4-updated-test-location").build();
        testee.updateUserLocation(updatedUserLocation);

        assertNotNull(testee);
        assertEquals(expectedUserLocations, testee.getOverallMap().getUserLocations());
        assertEquals("user-4-updated-test-location", testee.getSingleUserLocation(4).getLocation());
    }

    @Test
    void clearMap() {
        OverallMapControllerImpl expectedOverallMapController = new OverallMapControllerImpl();
        expectedOverallMapController.init();
        expectedOverallMapController.getOverallMap().setId(99999);
        expectedOverallMapController.getOverallMap().setUserLocations(new ArrayList<>());

        assertNotNull(testee.getOverallMap());
        testee.clearMap();

        assertEquals(expectedOverallMapController.getOverallMap(), testee.getOverallMap());
        assertEquals(99999, testee.getOverallMap().getId());
        assertEquals(new ArrayList<>(), testee.getOverallMap().getUserLocations());
    }
}