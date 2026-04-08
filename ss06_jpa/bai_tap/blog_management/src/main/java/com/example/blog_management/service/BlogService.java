package com.example.blog_management.service;

import com.example.blog_management.entity.Blog;
import com.example.blog_management.repository.IBlogRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService implements IBlogService{
    private final IBlogRepository blogRepository;
    public BlogService(IBlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }
    @Override
    public List<Blog> findAllBlogs() {
        return this.blogRepository.findAllBlogs();
    }

    @Override
    public Blog findBlogById(Long id) {
        return this.blogRepository.findBlogById(id);
    }

    @Override
    public void saveBlog(Blog blog) {
        this.blogRepository.saveBlog(blog);
    }

    @Override
    public void updateBlog(Blog blog) {
        this.blogRepository.updateBlog(blog);
    }

    @Override
    public void deleteBlog(Long id) {
        this.blogRepository.deleteBlog(id);
    }
}
