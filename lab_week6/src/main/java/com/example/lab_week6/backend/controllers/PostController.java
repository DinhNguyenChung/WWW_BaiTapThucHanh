package com.example.lab_week6.backend.controllers;

import com.example.lab_week6.backend.models.Post;
import com.example.lab_week6.backend.models.User;
import com.example.lab_week6.backend.repositories.PostRepository;
import com.example.lab_week6.backend.services.PostService;
import com.example.lab_week6.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserService userService;



    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("posts", postService.getAllPosts());
        return "home";
    }

    @GetMapping("/post/{id}")
    public String postDetail(@PathVariable Long id, Model model) {
        Optional<Post> post = postService.getPostById(id);
        post.ifPresent(value -> model.addAttribute("post", value));
        return post.map(p -> "postDetail").orElse("error");
    }

}
