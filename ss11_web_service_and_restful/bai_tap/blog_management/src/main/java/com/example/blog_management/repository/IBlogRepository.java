package com.example.blog_management.repository;

import com.example.blog_management.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBlogRepository extends JpaRepository<Blog, Long> {
    Page<Blog> findAllByOrderByCreatedAtDesc(Pageable pageable);
    Page<Blog> findAllByCategory_Id(Long categoryId, Pageable pageable);
    Page<Blog> findAllByTitleContainingIgnoreCase(String title, Pageable pageable);
    Page<Blog> findAllByTitleContainingIgnoreCaseAndCategory_Id(String title, Long categoryId, Pageable pageable);
}
