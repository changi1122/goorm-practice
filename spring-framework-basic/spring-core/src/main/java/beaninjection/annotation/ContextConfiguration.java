package beaninjection.annotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "beaninjection.common", "beaninjection.annotation" })
public class ContextConfiguration {

}
