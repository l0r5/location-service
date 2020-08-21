package com.example.locationservice.services;


import com.example.locationservice.controllers.OverallMapControllerImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.metamodel.EntityType;
import javax.transaction.Transactional;
import java.util.Set;

@Service
public class PersistenceServiceImpl implements PersistenceService {

    private static final Logger log = LoggerFactory.getLogger(PersistenceServiceImpl.class);

    private final OverallMapController overallMapController;

    @PersistenceContext
    private EntityManager entityManager;

    public PersistenceServiceImpl(OverallMapControllerImp overallMapController) {
        this.overallMapController = overallMapController;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void logDbOnStartup() {
        Set<EntityType<?>> registeredEntities = entityManager.getMetamodel().getEntities();
        if (registeredEntities.isEmpty()) {
            log.info("No entities for the OverallMap registered yet.");
        }
        registeredEntities.forEach(this::logEntities);
    }

    private void logEntities(EntityType<?> entityType) {
        log.info("{} is ignored in persistence context.", entityType.getName());
    }

    @Override
    @Scheduled(fixedRate = 60000) // 1 Minute
    @Transactional
    public void persistOverallMap() {
        entityManager.persist(overallMapController.getOverallMap());
        log.info("PeriodSync: OverallMap persisted after 1 Minute.");
    }
}