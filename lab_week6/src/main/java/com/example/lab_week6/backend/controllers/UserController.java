package com.example.lab_week6.backend.controllers;

import com.example.lab_week6.backend.DTO.UserDto;
import com.example.lab_week6.backend.models.User;
import com.example.lab_week6.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> addUser (@RequestBody UserDto userDTO){

        User savedUser = userService.UserSave(userDTO);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED); // Trả về HTTP 201 Created

    }
    // GET method to fetch all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
