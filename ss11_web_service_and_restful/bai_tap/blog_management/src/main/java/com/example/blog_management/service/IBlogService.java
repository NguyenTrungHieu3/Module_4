package com.example.blog_management.service;

import com.example.blog_management.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBlogService {
    Page<Blog> findAll(Pageable pageable);
    Blog save(Blog blog);
    Blog findById(Long id);
    void deleteById(Long id);
    Page<Blog> searchByCategoryId(Long categoryId, Pageable pageable);
    Page<Blog> searchByTitle(String title, Pageable pageable);
    Page<Blog> searchByTitleAndCategoryId(String title, Long categoryId, Pageable pageable);
}
