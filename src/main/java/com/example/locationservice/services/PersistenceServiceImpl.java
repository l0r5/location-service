package com.example.locationservice.services;


import com.example.locationservice.models.OverallMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Service
public class PersistenceServiceImpl implements PersistenceService{

    private static final Logger log = LoggerFactory.getLogger(PersistenceServiceImpl.class);

    private final OverallMap overallMap;

    @PersistenceContext
    private EntityManager entityManager;

    public PersistenceServiceImpl(OverallMap overallMap) {
        this.overallMap = overallMap;
    }

    @Override
    @Scheduled(fixedRate = 60000) // 1 Minute
    @Transactional
    public void persistOverallMap() {
        entityManager.merge(this.overallMap);
        log.info("PeriodSync: OverallMap persisted after 1 Minute.");
    }
}