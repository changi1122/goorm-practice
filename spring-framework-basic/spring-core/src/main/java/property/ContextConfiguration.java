package property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

@PropertySource("property/items.properties")
public class ContextConfiguration {

    @Bean
    Item milkies(@Value("${item1.name}") String name,
                 @Value("${item1.price}") int price) {
        return new Item(name, price);
    }

    @Bean
    Item cantata(@Value("${item2.name}") String name,
                 @Value("${item2.price}") int price) {
        return new Item(name, price);
    }

}
