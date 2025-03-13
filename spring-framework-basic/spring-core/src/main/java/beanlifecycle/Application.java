package beanlifecycle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class Application {

    public static void main(String[] args) {
        System.out.println("====== PostConstruct, PreDestroy로 FileWriter 열기/닫기 ======\n");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ContextConfiguration.class);

        FileWriteService service = context.getBean(FileWriteService.class);
        System.out.println("파일에 Hello World라 씁니다.");
        try {
            service.writeString("Hello World");
        } catch (IOException ex) {
            System.out.println("파일 쓰는 중 오류 발생!");
        }

        context.close();
    }
}
