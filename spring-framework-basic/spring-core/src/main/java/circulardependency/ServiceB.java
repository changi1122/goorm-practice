package circulardependency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class ServiceB {

    private ServiceB serviceB;

    @Autowired  // setter 주입으로 변경
    public void setServiceB(ServiceB serviceB) {
        this.serviceB = serviceB;
    }

    @PostConstruct
    public void init() {
        // ServiceA에 의존적인 초기화 작업은 PostConstruct에서 수행
    }
}
