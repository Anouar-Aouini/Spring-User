package com.fivepoints.spring.repositories;

import com.fivepoints.spring.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
// Now we can use JpaRepository’s methods: save(), findOne(), findById(), findAll(), count(), delete(),
// deleteById()… and more. without implementing these methods.
// We also define custom finder methods: findByPublished & findByTitleContaining.
public interface PostRepository extends JpaRepository<Post, Long> {
    // Returns all Posts with published having value as input published.
    List<Post> findByPublished(boolean published);
    //  Returns all Posts which title contains input title.
    List<Post> findByTitleContaining(String title);
}
