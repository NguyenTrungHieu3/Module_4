package com.example.blog_management.service;

import com.example.blog_management.entity.Blog;
import com.example.blog_management.repository.IBlogRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService implements IBlogService{
    private final IBlogRepository blogRepository;
    public BlogService(IBlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public Page<Blog> findAll(Pageable pageable) {
        return this.blogRepository.findAll(pageable);
    }

    @Override
    public Blog save(Blog blog) {
        return this.blogRepository.save(blog);
    }

    @Override
    public Blog findById(Long id) {
        return this.blogRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        this.blogRepository.deleteById(id);
    }

    @Override
    public Page<Blog> searchByCategoryId(Long categoryId, Pageable pageable) {
        return this.blogRepository.findAllByCategory_Id(categoryId, pageable);
    }

    @Override
    public Page<Blog> searchByTitle(String title, Pageable pageable) {
        return this.blogRepository.findAllByTitleContainingIgnoreCase(title, pageable);
    }

    @Override
    public Page<Blog> searchByTitleAndCategoryId(String title, Long categoryId, Pageable pageable) {
        return this.blogRepository.findAllByTitleContainingIgnoreCaseAndCategory_Id(title, categoryId, pageable);
    }
}
