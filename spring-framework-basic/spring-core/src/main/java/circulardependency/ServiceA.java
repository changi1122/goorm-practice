package circulardependency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceA {

    private ServiceB serviceB;

    @Autowired
    public ServiceB getServiceB(ServiceB serviceB) {
        return serviceB;
    }

    public void printSomething() {
        System.out.println("작동 확인!!");
    }
}
