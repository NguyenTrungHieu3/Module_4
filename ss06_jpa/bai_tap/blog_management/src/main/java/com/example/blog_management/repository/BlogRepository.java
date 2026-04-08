package com.example.blog_management.repository;

import com.example.blog_management.entity.Blog;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BlogRepository implements IBlogRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Blog> findAllBlogs() {
        TypedQuery<Blog> query = entityManager.createQuery("FROM Blog ", Blog.class);
        return query.getResultList();
    }

    @Override
    public Blog findBlogById(Long id) {
        return entityManager.find(Blog.class, id);
    }

    @Transactional
    @Override
    public void saveBlog(Blog blog) {
        entityManager.persist(blog);
    }

    @Transactional
    @Override
    public void updateBlog(Blog blog) {
        entityManager.merge(blog);
    }

    @Transactional
    @Override
    public void deleteBlog(Long id) {
        Blog blog = entityManager.find(Blog.class, id);
        if (blog != null) {
            entityManager.remove(blog);
        }
    }
}
