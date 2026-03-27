package com.example.blog_application.service;

import com.example.blog_application.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPostService {
    void savePost(Post post);

    void deletePost(Long id);

    Post getPost(Long id);

    Page<Post> findAllPosts(Pageable pageable);

    Post findById(Long id);

    Page<Post> search(String keyword, Pageable pageable);

    Page<Post> findByCategory(Long categoryId, Pageable pageable);
}
