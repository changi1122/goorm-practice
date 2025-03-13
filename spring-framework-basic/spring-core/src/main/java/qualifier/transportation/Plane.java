package qualifier.transportation;

import org.springframework.stereotype.Component;

@Component
public class Plane implements Transportation {

    @Override
    public void move() {
        System.out.println("비행기로 이동합니다 :::    >->-                :::");
    }
}
