package net.studio1122;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        // Application Context 생성
        ApplicationContext context = new GenericXmlApplicationContext("application.xml");

        System.out.println("[생성된 빈 목록 출력] --------");
        Arrays.stream(context.getBeanDefinitionNames())
                        .forEach(System.out::println);

        // context에서 UserService 빈을 가져오기
        UserService service = context.getBean(UserService.class);
        
        // 유저 생성
        User user1 = service.createUser("username1", "password1");
        User user2 = service.createUser("username2", "password2");

        System.out.println();

        // UserDAO에서 조회한 User 객체 출력
        User findUser = service.getUser(user1.getId());
        System.out.println("findUser = " + findUser);

        System.out.println();

        // 전체 회원 수 출력
        long userSize = service.getUserSize();
        System.out.println("userSize = " + userSize);
    }
}