package beaninjection.javaconfig;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ContextConfiguration.class);

        InjectService service = context.getBean(InjectService.class);

        System.out.println("====== JavaConfig로 InjectService에 TestDAO 주입, 작동 확인 ======");

        System.out.println("service.testDAO.getClass().getName(): " + service.getTestDAOType());
        System.out.println("service.getClass().getName(): " + service.getClass().getName());
        service.insert("test");
        System.out.println("testDAO의 List에 문자열 추가 후 count(): " + service.count());
    }
}
