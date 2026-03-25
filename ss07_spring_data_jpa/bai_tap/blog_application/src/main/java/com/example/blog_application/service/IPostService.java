package com.example.blog_application.service;

import com.example.blog_application.entity.Post;

import java.util.List;

public interface IPostService {
    void savePost(Post post);
    void deletePost(Long id);
    Post getPost(Long id);
    List<Post> getAllPosts();
}
