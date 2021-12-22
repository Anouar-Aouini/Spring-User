package com.fivepoints.spring.services;

import com.fivepoints.spring.entities.Post;
import com.fivepoints.spring.exceptions.ResourceNotFoundException;
import com.fivepoints.spring.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    public Post saveNewPost(Post post)
    {
        return this.postRepository.save(post);
    }

    public List<Post> getAllPost()
    {
        return this.postRepository.findAll();
    }

    public Post findPostByID(long id)
    {
        Optional<Post> postData = this.postRepository.findById(id);
        // Return statement if user exist or null
        return postData.orElseThrow(() -> new ResourceNotFoundException("Post not found"));

    }

    public String updatePostByID(long id, Post post)
    {
        Optional<Post> postData = this.postRepository.findById(id);
        if (postData.isPresent()) {
            Post existingPost = postData.orElse(null);
            existingPost.setTitle(post.getTitle());
            existingPost.setDescription(post.getDescription());
            existingPost.setPublished(post.isPublished());
            // save existingUser in the database
            this.postRepository.save(existingPost);
            // return statement
            return "Post updated successfully!";
        } else {
            throw new ResourceNotFoundException("Post not found");
        }
    }

    public String deletePostById(long id)
    {
        Optional<Post> postData = this.postRepository.findById(id);
        if (postData.isPresent()) {
            this.postRepository.deleteById(id);
            return "Post deleted successfully!";
        } else {
            throw new ResourceNotFoundException("Post not found");
        }
    }

    public List<Post> findByPublished()
    {
        return  this.postRepository.findByPublished(true);
    }

    public List<Post> findByTitleContaining(String title)
    {
        return  this.postRepository.findByTitleContaining(title);
    }
}
