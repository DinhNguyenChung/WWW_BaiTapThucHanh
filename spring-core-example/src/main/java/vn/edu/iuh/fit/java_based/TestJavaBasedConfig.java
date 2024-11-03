package vn.edu.iuh.fit.java_based;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestJavaBasedConfig {

    public static void main(String[] args) {
        ApplicationContext ctx;
//        ctx = new AnnotationConfigApplicationContext(UserFactoryService.class);
        ctx = new AnnotationConfigApplicationContext("vn.edu.iuh.fit.javabased");
        User us = ctx.getBean(User.class);
        System.out.println(us);
        Account ac = ctx.getBean(Account.class);
        System.out.println(ac);
    }
}
