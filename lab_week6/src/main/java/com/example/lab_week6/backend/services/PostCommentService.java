package com.example.lab_week6.backend.services;

import com.example.lab_week6.backend.models.PostComment;
import com.example.lab_week6.backend.repositories.PostCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostCommentService {
    @Autowired
    private PostCommentRepository postCommentRepository;

    public List<PostComment> getCommentsByPostId(Long postId) {
        return postCommentRepository.findByPostId(postId);
    }
    public void save(PostComment postComment) {
        postCommentRepository.save(postComment);
    }
    public PostComment getCommentById(Long id) {
        return postCommentRepository.findById(id).orElse(null);
    }
}
