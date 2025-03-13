package primary.transportation;

public class Plane implements Transportation {

    @Override
    public void move() {
        System.out.println("비행기로 이동합니다 :::    >->-                :::");
    }
}
