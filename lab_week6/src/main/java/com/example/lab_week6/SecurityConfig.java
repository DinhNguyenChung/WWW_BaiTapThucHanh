package com.example.lab_week6;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http
//                .csrf(csrf -> csrf.disable()) // Tắt CSRF (hoặc cấu hình chi tiết hơn)
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/signUp", "/").permitAll() // Cho phép truy cập endpoint không yêu cầu xác thực
//                        .anyRequest().authenticated() // Các endpoint khác cần xác thực
//                )
//                .build();
//    }
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll() // Tất cả các URL đều cho phép truy cập không cần xác thực
            ).csrf(csrf -> csrf.disable()) // Tắt CSRF nếu không cần thiết để tránh lỗi bảo mật (tùy trường hợp).
            .formLogin(form -> form.disable()) // Tắt form login mặc định của Spring Security
            .logout(logout -> logout.logoutUrl("/logout") // URL để đăng xuất
                    .permitAll());
    return http.build();
}


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
