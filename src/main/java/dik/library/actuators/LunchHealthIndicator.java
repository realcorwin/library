package dik.library.actuators;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class LunchHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        if (LocalTime.now().getHour() == 13) {
            return Health.down()
                    .status(Status.DOWN)
                    .withDetail("message", "Закрыто на обед!")
                    .build();
        } else {
            return Health.up().build();
        }
    }
}
