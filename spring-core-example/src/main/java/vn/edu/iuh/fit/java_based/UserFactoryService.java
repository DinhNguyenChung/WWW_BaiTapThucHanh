package vn.edu.iuh.fit.java_based;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration//ensuring spring context scan this class
public class UserFactoryService {
    @Bean
    public Group getGroup() {
        return new Group("Nhom buon dua le");
    }

    @Bean
    public User getUser() {
        return new User("No-Name","no-pass",getGroup());
    }
}
