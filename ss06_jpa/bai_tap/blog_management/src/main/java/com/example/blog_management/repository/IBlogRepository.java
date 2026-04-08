package com.example.blog_management.repository;

import com.example.blog_management.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IBlogRepository {
    List<Blog> findAllBlogs();

    Blog findBlogById(Long id);

    void saveBlog(Blog blog);

    void updateBlog(Blog blog);

    void deleteBlog(Long id);
}
