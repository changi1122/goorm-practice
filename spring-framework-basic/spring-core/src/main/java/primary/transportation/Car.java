package primary.transportation;

public class Car implements Transportation {

    @Override
    public void move() {
        System.out.println("자동차로 이동합니다.");
    }
}
