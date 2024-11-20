package com.in.sp.controller;

import com.in.sp.entity.Post;
import com.in.sp.repository.CommentRepository;
import com.in.sp.repository.PostRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    private PostRepository postRepository;
    private CommentRepository commentRepository;


    public PostController(PostRepository postRepository, CommentRepository commentRepository){

        this.postRepository=postRepository;
        this.commentRepository=commentRepository;
    }

    @PostMapping
    public String createPost(
            @RequestBody Post post
    ){

        postRepository.save(post);

        return "saved";
    }

    @DeleteMapping
    public String  deletPost(){
        postRepository.deleteById(1L);

    return "deleted sussessfully";
    }

}
