package vn.edu.iuh.fit.java_based;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class X {
    @Bean
    public Account getAccount() {
        return new Account();
    }
}
