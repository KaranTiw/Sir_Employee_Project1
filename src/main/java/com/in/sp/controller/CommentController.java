package com.in.sp.controller;

import com.in.sp.entity.Comment;
import com.in.sp.entity.Post;
import com.in.sp.repository.CommentRepository;
import com.in.sp.repository.PostRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {

    private CommentRepository commentRepository;
    private PostRepository postRepository;

    public CommentController(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository=commentRepository;
        this.postRepository=postRepository;
    }

    @PostMapping
    public String createComment(
            @RequestBody Comment comment,
            @RequestParam Long postId
            ){


        Post post = postRepository.findById(postId).get();

        comment.setPost(post);
        commentRepository.save(comment);
        return "Comment created successfully";


    }




}
