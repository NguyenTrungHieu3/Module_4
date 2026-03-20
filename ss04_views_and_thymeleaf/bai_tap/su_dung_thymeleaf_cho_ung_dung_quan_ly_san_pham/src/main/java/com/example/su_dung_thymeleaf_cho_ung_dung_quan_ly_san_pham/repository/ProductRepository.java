package com.example.su_dung_thymeleaf_cho_ung_dung_quan_ly_san_pham.repository;

import com.example.su_dung_thymeleaf_cho_ung_dung_quan_ly_san_pham.entity.Product;
import com.example.su_dung_thymeleaf_cho_ung_dung_quan_ly_san_pham.util.ConnectionUtil;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository implements IProductRepository {
    @Override
    public List<Product> getAllProducts() {
        Session session = ConnectionUtil.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        TypedQuery<Product> query = session.createQuery("FROM Product", Product.class);
        List<Product> products = query.getResultList();
        transaction.commit();
        session.close();
        return products;
    }

    @Override
    public boolean addProduct(Product product) {
        Session session = ConnectionUtil.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(product);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean updateProduct(Product product) {
        Session session = ConnectionUtil.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(product);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean deleteProductById(int id) {
        Session session = ConnectionUtil.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Product product = session.get(Product.class, id);
        session.delete(product);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Product getProductById(int id) {
        Session session = ConnectionUtil.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Product product = session.get(Product.class, id);
        transaction.commit();
        session.close();
        return product;
    }

    @Override
    public List<Product> searchProductsByName(String name) {
        Session session = ConnectionUtil.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        TypedQuery<Product> query = session.createQuery("FROM Product WHERE name LIKE :name", Product.class);
        query.setParameter("name", "%" + name + "%");
        List<Product> products = query.getResultList();
        transaction.commit();
        session.close();
        return products;
    }
}
