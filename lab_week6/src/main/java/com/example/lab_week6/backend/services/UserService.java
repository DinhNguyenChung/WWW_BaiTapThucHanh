package com.example.lab_week6.backend.services;

import com.example.lab_week6.backend.DTO.UserDto;
import com.example.lab_week6.backend.models.User;
import com.example.lab_week6.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

//    private PasswordEncoder passwordEncoder;
    public User UserSave(UserDto userDto){
        // Chuyển đổi từ DTO sang Entity
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setMiddleName(userDto.getMiddleName());
        user.setLastName(userDto.getLastName());
        user.setMobile(userDto.getMobile());
        user.setEmail(userDto.getEmail());
        user.setPasswordHash(userDto.getPasswordHash());
        user.setRegisteredAt(userDto.getRegisteredAt());
        user.setLastLogin(userDto.getLastLogin());
        user.setIntro(userDto.getIntro());
        user.setProfile(userDto.getProfile());
        return userRepository.save(user);
    }
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    public User getUserById(Long id){
       return userRepository.findById(id).orElse(null);
    }

}
