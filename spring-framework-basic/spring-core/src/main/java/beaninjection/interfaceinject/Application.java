package beaninjection.interfaceinject;

import beaninjection.annotation.ContextConfiguration;
import beaninjection.common.TestDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ContextConfiguration.class);

        TestDAO testDAO = context.getBean(TestDAO.class);

        System.out.println("====== TestDAO 인터페이스를 통해 TestDAOImpl 의존성 주입 ======");
        System.out.println("testDAO.getClass().getName(): " + testDAO.getClass().getName());
    }
}
