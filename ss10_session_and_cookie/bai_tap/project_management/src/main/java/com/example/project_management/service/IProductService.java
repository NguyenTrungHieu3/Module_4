package com.example.project_management.service;

import com.example.project_management.entity.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAllProducts();
    Product findProductById(Long id);
    void save(Product product);
}