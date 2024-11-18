package com.example.lab_week6.backend.repositories;

import com.example.lab_week6.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

//    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
    User findByEmail(String email);

}
