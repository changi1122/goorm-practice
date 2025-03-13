package primary;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import primary.transportation.Transportation;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ContextConfiguration.class);

        System.out.println("====== 예상 결과: @Primary 어노테이션을 통해 Plane Bean이 선택됩니다 ======");
        System.out.println("[빈 목록]: " +
                Arrays.stream(context.getBeanDefinitionNames())
                        .map(name -> name.substring(Math.max(0, name.length() - 15), name.length()))
                        .collect(Collectors.joining(", "))
        );

        Transportation trasportation = context.getBean(Transportation.class);
        System.out.println("\n선택된 교통수단 : " + trasportation.getClass().getName());
        trasportation.move();
    }
}
