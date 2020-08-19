package com.example.locationservice.services;

import com.example.locationservice.models.OverallMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ImportAutoConfiguration(classes = {OverallMap.class, PersistenceServiceImpl.class})
class PersistenceServiceTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private OverallMap overallMap;

    @Autowired
    private PersistenceService persistenceService;

    @BeforeEach
    void setUp() {
        Map<Integer, String> userLocations = new HashMap<>();
        userLocations.put(1, "test-location-1");
        userLocations.put(2, "test-location-2");
        userLocations.put(3, "test-location-3");
        overallMap.setUserLocations(userLocations);
    }

    @Test
    void persistOverallMap() {
        OverallMap testResult = entityManager.find(OverallMap.class, (long) 1);
        assertEquals(new HashMap<>(), testResult.getAllUserLocations());
        persistenceService.persistOverallMap();
//        testResult = this.entityManager.find(OverallMap.class, (long) 1);
        //TODO: Implement
        //        assertEquals(1, testResult.getId());
//        assertEquals("", testResult.getAllUserLocations().toString());
    }
}