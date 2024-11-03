package vn.edu.iuh.fit.demo_autowire;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import vn.edu.iuh.fit.configs.AppConfig;

public class Test {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        HumanTransportServices services = context.getBean(HumanTransportServices.class);
        GoodTransportServices goodServices = context.getBean(GoodTransportServices.class);
        goodServices.running();
        services.running();
    }
}
