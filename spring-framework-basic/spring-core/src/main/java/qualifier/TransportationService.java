package qualifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import qualifier.transportation.Transportation;

@Service
public class TransportationService {

    private Transportation transportation; // 빈을 Autowired로 주입 받음

    @Autowired
    public TransportationService(@Qualifier("car") Transportation transportation) {
        this.transportation = transportation;
    }

    public Transportation getTransportation() {
        return transportation;
    }

    public void setTransportation(Transportation transportation) {
        this.transportation = transportation;
    }
}
