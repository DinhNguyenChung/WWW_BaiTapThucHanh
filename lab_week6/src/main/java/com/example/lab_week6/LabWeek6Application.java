package com.example.lab_week6;

import com.example.lab_week6.backend.DTO.UserDto;
import com.example.lab_week6.backend.models.User;
import com.example.lab_week6.backend.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class LabWeek6Application {

    public static void main(String[] args) {
        SpringApplication.run(LabWeek6Application.class, args);
    }
//    @Bean
//    public CommandLineRunner run(UserService userService) {
//        return args -> {
//            UserDto userDto = new UserDto(
//                    null, "John", "Doe", "Smith", "1234567890", "john.doe@example.com", "hashedpassword", new Date(), new Date(), "Hello, I'm John", "profile_image.jpg"
//            );
//
//            // Thêm người dùng mới
//            userService.UserSave(userDto);
//            System.out.println("User added successfully.");
//        };
//    }

}
