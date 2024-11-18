package com.example.lab_week6.backend.services;

import com.example.lab_week6.backend.models.Post;
import com.example.lab_week6.backend.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }
    public Post getOnePostById(Long id) {
        return postRepository.findById(id).get();
    }
}
