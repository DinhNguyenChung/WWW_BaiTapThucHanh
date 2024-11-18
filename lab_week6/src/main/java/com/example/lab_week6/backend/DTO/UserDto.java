package com.example.lab_week6.backend.DTO;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * DTO for {@link com.example.lab_week6.backend.models.User}
 */
@Value
@Getter
@Setter

public class UserDto implements Serializable {
    Long id;
    String firstName;
    String middleName;
    String lastName;
    String mobile;
    String email;
    String passwordHash;
    LocalDateTime registeredAt;
    LocalDateTime lastLogin;
    String intro;
    String profile;
}