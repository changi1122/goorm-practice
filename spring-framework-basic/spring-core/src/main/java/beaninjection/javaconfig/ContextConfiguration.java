package beaninjection.javaconfig;

import beaninjection.common.TestDAO;
import beaninjection.common.TestDAOImpl;
import org.springframework.context.annotation.Bean;

public class ContextConfiguration {

    @Bean
    public TestDAO testDAO() {
        return new TestDAOImpl();
    }

    @Bean
    public InjectService injectService() {
        return new InjectService(testDAO());
    }
}
