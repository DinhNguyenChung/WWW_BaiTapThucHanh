package vn.edu.iuh.fit.demo_autowire;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class Truck implements Vehicle {
    @Override
    public void run() {
        System.out.println("The truck is running");
    }
}
