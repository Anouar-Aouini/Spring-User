package com.fivepoints.spring.controllers;

import com.fivepoints.spring.entities.Post;
import com.fivepoints.spring.payload.responses.MessageResponse;
import com.fivepoints.spring.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("posts")
@CrossOrigin(origins = "http://localhost:4200")
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping("/")
    public ResponseEntity<Post> saveNewPost(@RequestBody Post post)
    {
        Post savedPost =  this.postService.saveNewPost(post);
        return new ResponseEntity<>(savedPost, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Post>> getAllPosts()
    {
        List<Post> listPosts = this.postService.getAllPost();
        return new ResponseEntity<>(listPosts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findPostByID(@PathVariable("id") long id)
    {
        Post post = this.postService.findPostByID(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageResponse> updateUserByID(@PathVariable("id") long id, @RequestBody Post post)
    {
        String message = this.postService.updatePostByID(id, post);
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteUserById(@PathVariable("id") long id)
    {
        String message = this.postService.deletePostById(id);
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
    }
    
    @GetMapping("/only-published")
    public ResponseEntity<List<Post>> findByPublished()
    {
        List<Post> listPublishedPosts = this.postService.findByPublished();
        return new ResponseEntity<>(listPublishedPosts, HttpStatus.OK);
    }

    @GetMapping("/search-by-title/{title}")
    public ResponseEntity<List<Post>> findByTitleContaining(@PathVariable("title") String title)
    {
        List<Post> filtredPosts = this.postService.findByTitleContaining(title);
        return new ResponseEntity<>(filtredPosts, HttpStatus.OK);
    }
}
