package primary;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import primary.transportation.Bus;
import primary.transportation.Car;
import primary.transportation.Plane;
import primary.transportation.Transportation;

public class ContextConfiguration {

    @Bean("plane")
    @Primary
    public Transportation plane() {
        return new Plane();
    }

    @Bean("bus")
    public Transportation bus() {
        return new Bus();
    }

    @Bean("car")
    public Transportation car() {
        return new Car();
    }
}
