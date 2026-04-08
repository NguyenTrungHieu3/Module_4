package com.example.blog_management.service;

import com.example.blog_management.entity.Category;

import java.util.List;

public interface ICategoryService {
    Category save(Category category);
    List<Category> findAll();
    Category findById(Long id);
    void deleteById(Long id);
}
