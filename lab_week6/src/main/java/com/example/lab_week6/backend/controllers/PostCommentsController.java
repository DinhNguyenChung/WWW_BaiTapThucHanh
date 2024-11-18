package com.example.lab_week6.backend.controllers;

import com.example.lab_week6.backend.models.Post;
import com.example.lab_week6.backend.models.PostComment;
import com.example.lab_week6.backend.models.User;
import com.example.lab_week6.backend.repositories.PostRepository;
import com.example.lab_week6.backend.services.PostCommentService;
import com.example.lab_week6.backend.services.PostService;
import com.example.lab_week6.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
public class PostCommentsController {
    @Autowired
    private PostCommentService postCommentService;
    @Autowired
    private PostController postController;
    @Autowired
    private AuthController authController;
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;
    @Autowired
    private PostRepository postRepository;

    @PostMapping("/parentComment")
    public String  AddParentComment(Model model, @RequestParam("parentComment") String parentComment,
                                    @RequestParam("postid") Long postid,
                                    @RequestParam("userid") Long userid
    ){
        PostComment postComment = new PostComment();
        postComment.setContent(parentComment);
        Post post = postService.getOnePostById(postid);
        postComment.setPost(post);
        User user = userService.getUserById(userid);
        postComment.setUser(user);
        postComment.setPublished(true);
        postComment.setCreatedAt(LocalDateTime.now());
        postComment.setPublishedAt(LocalDateTime.now());
        postComment.setTitle("parent comment");
        postCommentService.save(postComment);
        model.addAttribute("user", user);

        return postController.homePage(model);

    }
    @PostMapping("/childComment")
    public String  AddChildComment(Model model, @RequestParam("childComment") String childComment,
                                    @RequestParam("postid") Long postid,
                                    @RequestParam("userid") Long userid,
                                   @RequestParam("commentid") Long commentid
    ){
        PostComment postComment = new PostComment();
        postComment.setContent(childComment);
        Post post = postService.getOnePostById(postid);
        postComment.setPost(post);
        User user = userService.getUserById(userid);
        postComment.setUser(user);
        postComment.setPublished(true);
        postComment.setCreatedAt(LocalDateTime.now());
        postComment.setPublishedAt(LocalDateTime.now());
        postComment.setTitle("parent comment");
         PostComment postComment1= postCommentService.getCommentById(commentid);
        postComment.setParent(postComment1);
        postCommentService.save(postComment);
        model.addAttribute("user", user);

        return postController.homePage(model);

    }
    @PostMapping("/newPost")
    public String addPost(Model model,
                          @RequestParam("tittle")String tittle,
                          @RequestParam("content") String content,
                          @RequestParam("userid") Long userid
    ){
        Post post = new Post();
        post.setTitle(tittle);
        post.setContent(content);
        post.setCreatedAt(LocalDateTime.now());
        post.setPublished(true);

        User user = userService.getUserById(userid);
        post.setAuthor(user);
        postRepository.save(post);
        model.addAttribute("user", user);
        return postController.homePage(model);
    }

}
