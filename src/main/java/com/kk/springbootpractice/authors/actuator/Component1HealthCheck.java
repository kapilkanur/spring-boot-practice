package com.kk.springbootpractice.authors.actuator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

@Component
public class Component1HealthCheck implements HealthIndicator {

    @Override
    public Health health() {
        boolean running = true;

        if (running) {
            return Health.up().withDetail("component1", "value1").status(Status.UP).build();
        } else {
            return Health.down().withDetail("component1", "component1 is failing due to some error").status(Status.DOWN).build();
        }
    }
}
