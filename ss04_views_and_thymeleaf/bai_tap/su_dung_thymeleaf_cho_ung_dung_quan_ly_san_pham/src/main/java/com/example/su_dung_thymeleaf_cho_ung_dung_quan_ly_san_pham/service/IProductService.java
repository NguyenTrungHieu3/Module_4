package com.example.su_dung_thymeleaf_cho_ung_dung_quan_ly_san_pham.service;

import com.example.su_dung_thymeleaf_cho_ung_dung_quan_ly_san_pham.entity.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAllProducts();
    boolean addProduct(Product product);
    boolean updateProduct(Product product);
    boolean deleteProductById(int id);
    Product getProductById(int id);
    List<Product> searchProductsByName(String name);
}
