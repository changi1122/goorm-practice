package primary.transportation;

public class Bus implements Transportation {

    @Override
    public void move() {
        System.out.println("버스를 타고 이동합니다.~~~~~");
    }
}
