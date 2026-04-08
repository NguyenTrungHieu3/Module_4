package com.example.blog_management.service;

import com.example.blog_management.entity.Blog;

import java.util.List;

public interface IBlogService {
    List<Blog> findAllBlogs();

    Blog findBlogById(Long id);

    void saveBlog(Blog blog);

    void updateBlog(Blog blog);

    void deleteBlog(Long id);
}
