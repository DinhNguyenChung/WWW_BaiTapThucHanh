package vn.edu.iuh.fit.demo_autowire;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
public class GoodTransportServices {
    private  final Vehicle vehicle;
    public GoodTransportServices(@Qualifier("train") Vehicle vehicle) {
        this.vehicle = vehicle;
    }
    public void running() {
         vehicle.run();
    }
}
