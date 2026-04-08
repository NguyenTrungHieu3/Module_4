package com.example.su_dung_thymeleaf_cho_ung_dung_quan_ly_san_pham.repository;

import com.example.su_dung_thymeleaf_cho_ung_dung_quan_ly_san_pham.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository implements IProductRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Product> getAllProducts() {
        TypedQuery<Product> query = entityManager.createQuery("FROM Product ", Product.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public boolean addProduct(Product product) {
        entityManager.persist(product);
        return true;
    }

    @Transactional
    @Override
    public boolean updateProduct(Product product) {
        entityManager.merge(product);
        return true;
    }

    @Transactional
    @Override
    public boolean deleteProductById(int id) {
        Product product = getProductById(id);
        if (product != null) {
            entityManager.remove(product);
            return true;
        }
        return false;
    }

    @Override
    public Product getProductById(int id) {
        return entityManager.find(Product.class, id);
    }

    @Override
    public List<Product> searchProductsByName(String name) {
        TypedQuery<Product> query = entityManager.createQuery("FROM Product WHERE name LIKE :name", Product.class);
        query.setParameter("name", "%"+name+"%");
        return query.getResultList();
    }
}
