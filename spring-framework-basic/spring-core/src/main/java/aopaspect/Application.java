package aopaspect;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = new AnnotationConfigApplicationContext(ContextConfiguration.class);

        TargetService service = context.getBean(TargetService.class);

        System.out.println("======== 랜덤한 시간 sleep하는 slowMethod를 실행합니다! ========");
        service.slowMethod();

        System.out.println("\n======== 런타임 예외를 던지는 throwExceptionMethod를 실행합니다.! ========");
        try {
            service.throwExceptionMethod();
        } catch (Exception e) {}

    }
}
