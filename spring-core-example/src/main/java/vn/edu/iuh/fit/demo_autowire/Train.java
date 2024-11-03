package vn.edu.iuh.fit.demo_autowire;

import org.springframework.stereotype.Component;

@Component
public class Train implements Vehicle {
    @Override
    public void run() {
        System.out.println("The train is running");
    }
}
