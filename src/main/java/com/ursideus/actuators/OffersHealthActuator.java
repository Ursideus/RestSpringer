package com.ursideus.actuators;

import com.ursideus.entities.Offer;
import com.ursideus.services.OffersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by dovw on 11/25/15.
 */
@Component
public class OffersHealthActuator implements HealthIndicator {

    @Autowired
    private OffersService offersService;

    @Override
    public Health health() {
        Collection<Offer> offers = offersService.findAll();
        if (offers == null || offers.size() == 0)
            return Health.down().withDetail("offer count", 0).build();
        return Health.up().withDetail("offer count", offers.size()).build();
    }
}
