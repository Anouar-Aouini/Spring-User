package com.fivepoints.spring.controllers;

import com.fivepoints.spring.entities.Comment;
import com.fivepoints.spring.payload.requests.CreateCommentRequest;
import com.fivepoints.spring.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("comments")
@CrossOrigin(origins = "http://localhost:4200")
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping("/{postId}")
    public Page<Comment> getAllCommentsByPostId(@PathVariable(value = "postId") Long postId, Pageable pageable) {
        return this.commentService.getAllCommentsByPostId(postId, pageable);
    }

    @PostMapping("/{postId}")
    public Comment createComment(@PathVariable (value = "postId") Long postId,
                                 @Valid @RequestBody CreateCommentRequest createCommentRequest) {
        return  this.commentService.createComment(postId, createCommentRequest);
    }

}
