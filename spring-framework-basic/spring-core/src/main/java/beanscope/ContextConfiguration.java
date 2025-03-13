package beanscope;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ContextConfiguration {

    @Bean
    @Scope("singleton")
    public StringHolder singletonStringHolder() {
        return new StringHolder();
    }

    @Bean
    @Scope("prototype")
    public StringHolder prototypeStringHolder() {
        return new StringHolder();
    }
}
