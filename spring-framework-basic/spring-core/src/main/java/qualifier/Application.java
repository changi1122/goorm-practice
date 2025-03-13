package qualifier;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ContextConfiguration.class);

        System.out.println("====== 예상 결과: @Qualifier 어노테이션을 통해 Car Bean이 선택됩니다 ======");
        System.out.println("[빈 목록]: " +
                Arrays.stream(context.getBeanDefinitionNames())
                        .map(name -> name.substring(Math.max(0, name.length() - 22), name.length()))
                        .collect(Collectors.joining(", "))
        );

        TransportationService service = context.getBean(TransportationService.class);
        System.out.println("\n선택된 교통수단 : " + service.getTransportation().getClass().getName());
        service.getTransportation().move();
    }
}
