package vn.edu.iuh.fit.demo_autowire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
    public class HumanTransportServices {

    private final Vehicle vehicle;
    @Autowired
    public HumanTransportServices(@Qualifier("car") Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
    public void running(){
        vehicle.run();
    }
}
