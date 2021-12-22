package com.fivepoints.spring.services;

import com.fivepoints.spring.entities.Comment;
import com.fivepoints.spring.exceptions.ResourceNotFoundException;
import com.fivepoints.spring.payload.requests.CreateCommentRequest;
import com.fivepoints.spring.repositories.CommentRepository;
import com.fivepoints.spring.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;

    public Page<Comment> getAllCommentsByPostId(Long postId, Pageable pageable) {
        return commentRepository.findByPostId(postId, pageable);
    }

    public Comment createComment(Long postId, CreateCommentRequest createCommentRequest) {
        Comment comment = new Comment(createCommentRequest.getText());
        return postRepository.findById(postId).map(post -> {
            comment.setPost(post);
            return commentRepository.save(comment);
        }).orElseThrow(() -> new ResourceNotFoundException("Post not found"));
    }
}
