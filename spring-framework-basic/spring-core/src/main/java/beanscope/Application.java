package beanscope;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ContextConfiguration.class);

        System.out.println("========= Singleton Scope =========");
        StringHolder holder1 = context.getBean("singletonStringHolder", StringHolder.class);
        StringHolder holder2 = context.getBean("singletonStringHolder", StringHolder.class);
        holder1.addString(List.of("holder1에 Add", "테스트")); // holder1에만 String 추가

        System.out.println("holder1 = " + holder1.getStringList());
        System.out.println("holder2 = " + holder1.getStringList());

        System.out.println("holder1.hashCode() = " + holder1.hashCode()); // 예상 결과
        System.out.println("holder2.hashCode() = " + holder1.hashCode()); //  = 같음


        System.out.println("========= Prototype Scope =========");
        StringHolder pHolder1 = context.getBean("prototypeStringHolder", StringHolder.class);
        StringHolder pHolder2 = context.getBean("prototypeStringHolder", StringHolder.class);
        pHolder1.addString(List.of("holder1에 Add", "테스트")); // pHolder1에만 String 추가

        System.out.println("pHolder1 = " + pHolder1.getStringList());
        System.out.println("pHolder2 = " + pHolder2.getStringList());

        System.out.println("pHolder1.hashCode() = " + pHolder1.hashCode()); // 예상 결과
        System.out.println("pHolder2.hashCode() = " + pHolder2.hashCode()); //  = 다름
    }
}
