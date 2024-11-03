package vn.edu.iuh.fit.demo_autowire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class GoodTransportServices {
    private final Vehicle vehicle;
//    @Autowired
    public GoodTransportServices(@Qualifier("train") Vehicle vehicle) {
        this.vehicle = vehicle;
    }
    public void running(){
        vehicle.run();
    }
}
