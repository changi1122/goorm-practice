package property;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(ContextConfiguration.class);

        Item milkies = context.getBean("milkies", Item.class);
        Item cantata = context.getBean("cantata", Item.class);

        System.out.println("====== 프로퍼티 파일을 이용한 값 주입하기 ======");
        System.out.println("밀키스 = " + milkies);
        System.out.println("칸타타 = " + cantata);
    }
}
