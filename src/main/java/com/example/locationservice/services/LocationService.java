package com.example.locationservice.services;

import com.example.locationservice.dtos.OverallMapDto;
import com.example.locationservice.dtos.UserLocationDto;
import com.example.locationservice.entities.OverallMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Service
public class LocationService {

    private final Logger log = LoggerFactory.getLogger(LocationService.class);

    public LocationService() {
        log.info("Empty args constructor called.");
    }

    @PersistenceContext
    private EntityManager entityManager;

    @EventListener(classes = ApplicationReadyEvent.class)
    public void init() {
        log.info("LocationService is ready. Application has been started.");
    }


    @Transactional
    public OverallMapDto getOverallMapDto() {
        OverallMap overallMap = getOverallMap();
        log.info("OverallMap: {}", overallMap.toString());
        return overallMap.getOverallMapDto();
    }

    @Transactional
    public void addUserLocation(int uuid, String location) {
        log.info("Add UserLocation for [uuid]: {}", uuid);
        UserLocationDto newUserLocation = UserLocationDto.builder().uuid(uuid).location(location).build();
        OverallMap overallMap = getOverallMap();
        overallMap.addUserLocation(newUserLocation);
        entityManager.persist(overallMap);
        log.info("Added new UserLocation to the OverallMap: {}", overallMap.toString());
    }

    @Transactional
    public UserLocationDto getUserLocation(int uuid) {
        log.info("Get Location for [uuid]: {}", uuid);
        OverallMap overallMap = getOverallMap();
        String address = overallMap.getUserLocations().get(uuid);
        log.info("Got [address] {} for [uuid]: {}", address, uuid);
        return UserLocationDto.builder().uuid(uuid).location(address).build();
    }

    @Transactional
    public OverallMapDto updateUserLocation(int uuid, String location) {
        log.info("Update Location for [uuid]: {}", uuid);
        OverallMap overallMap = getOverallMap();
        overallMap.getUserLocations().replace(uuid, location);
        entityManager.persist(overallMap);
        log.info("User Location updated. Old [location]: {}; New [location]: {}", overallMap.getUserLocations().get(uuid), location);
        return overallMap.getOverallMapDto();
    }

    private OverallMap getOverallMap() {
        OverallMap result = entityManager.find(OverallMap.class, 1L);
        if (result == null) {
            log.info("OverallMap is null. Initialize OverallMap.");
            result = new OverallMap();
            log.info("OverallMap was created.");
            entityManager.persist(result);
            log.info("OverallMap persisted.");
        }
        return result;
    }
}
