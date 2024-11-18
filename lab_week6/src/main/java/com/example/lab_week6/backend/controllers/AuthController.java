package com.example.lab_week6.backend.controllers;

import com.example.lab_week6.backend.models.User;
import com.example.lab_week6.backend.repositories.UserRepository;
import com.example.lab_week6.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Controller
//@RequestMapping("/auth")

public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private PostController postController;

    @PostMapping("/signUp")
    public String signUp(
            @RequestParam("firstName") String firstName,
            @RequestParam(value = "middleName", required = false) String middleName,
            @RequestParam("lastName") String lastName,
            @RequestParam("mobile") String mobile,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("confirmPassword") String confirmPassword,
            @RequestParam(value = "intro", required = false) String intro,
            @RequestParam(value = "profile", required = false) String profile,
            Model model) {

        // Kiểm tra email đã tồn tại
        if (userRepository.existsByEmail(email)) {
//            model.addAttribute("error", "Email already exists!");
            model.addAttribute("message", "This email already exists");
            model.addAttribute("type", "error");
            return "home";
        }
        if(!password.equals(confirmPassword)) {

            model.addAttribute("message", "Passwords do not match");
            model.addAttribute("type", "error");
            return "home";
        }
        // Lưu thông tin người dùng
        User user = new User();
        user.setFirstName(firstName);
        user.setMiddleName(middleName);
        user.setLastName(lastName);
        user.setMobile(mobile);
        user.setEmail(email);
//        user.setPassword(password); // Lưu ý: Mã hóa mật khẩu trước khi lưu!
        user.setPasswordHash(passwordEncoder.encode(password));
        user.setIntro(intro);
        user.setProfile(profile);
        user.setRegisteredAt(LocalDateTime.now());

        userRepository.save(user);

//        model.addAttribute("success", "Sign up successful!");
        // Nếu thành công, gửi thông báo "success"

        model.addAttribute("message", "Sign up successful!");
        model.addAttribute("type", "success");
       return postController.homePage(model);

    }
    @PostMapping("/login")
    public String Login(Model model,@RequestParam("email") String email,@RequestParam("password") String password) {

        if(userRepository.existsByEmail(email)) {
            User user = userRepository.findByEmail(email);

//            if(password.equals(passwordEncoder.encode(password))) {}
           if(passwordEncoder.matches(password, user.getPasswordHash())){
               model.addAttribute("user", user);
               model.addAttribute("password",password);
               return postController.homePage(model);
            }

        }
        model.addAttribute("messageLogin", "Invalid email or password! Please go back to the homepage to log in.");

        return "home";
    }
    @GetMapping("/login")
    public String homeLogin(Model model,@RequestParam("email")String email,@RequestParam("password")String password) {
        return  Login(model,email,password);
    }

}
