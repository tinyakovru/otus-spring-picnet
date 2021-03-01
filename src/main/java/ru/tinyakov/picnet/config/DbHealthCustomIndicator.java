package ru.tinyakov.picnet.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
@Slf4j
public class DbHealthCustomIndicator implements HealthIndicator {
    private final DataSource dataSource;

    @Override
    public Health health() {
        try {
            if (dataSource.getConnection() != null) {
                return Health.up().build();
            }
        } catch (SQLException e) {
            log.error("no DB connection");
        }
        return Health.down()
                .status(Status.DOWN)
                .build();
    }

}
