package com.example.su_dung_thymeleaf_cho_ung_dung_quan_ly_san_pham.service;

import com.example.su_dung_thymeleaf_cho_ung_dung_quan_ly_san_pham.entity.Product;
import com.example.su_dung_thymeleaf_cho_ung_dung_quan_ly_san_pham.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements IProductService{
    @Autowired
    private IProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    @Override
    public boolean addProduct(Product product) {
        return productRepository.addProduct(product);
    }

    @Override
    public boolean updateProduct(Product product) {
        return productRepository.updateProduct(product);
    }

    @Override
    public boolean deleteProductById(int id) {
        if (getProductById(id) != null) {
            return productRepository.deleteProductById(id);
        }
        return false;
    }

    @Override
    public Product getProductById(int id) {
        return productRepository.getProductById(id);
    }

    @Override
    public List<Product> searchProductsByName(String name) {
        return productRepository.searchProductsByName(name);
    }
}
