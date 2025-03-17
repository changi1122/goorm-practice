package aopaspect;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class TargetService {

    public int slowMethod() throws InterruptedException {
        Random random = new Random();
        int time = Math.abs(random.nextInt() % 10);

        Thread.sleep(time * 1000L);

        System.out.println("[서비스 객체에서 출력] sleep 시간: " + time + "초");

        return time;
    }

    public int throwExceptionMethod() {
        throw new RuntimeException("런타임 예외 던지기");
    }
}
