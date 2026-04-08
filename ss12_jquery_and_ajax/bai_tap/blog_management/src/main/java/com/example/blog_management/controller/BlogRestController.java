package com.example.blog_management.controller;

import com.example.blog_management.dto.BlogDto;
import com.example.blog_management.entity.Blog;
import com.example.blog_management.entity.Category;
import com.example.blog_management.service.BlogService;
import com.example.blog_management.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/blogs")
public class BlogRestController {
    private final BlogService blogService;
    private final CategoryService categoryService;

    public BlogRestController(BlogService blogService, CategoryService categoryService) {
        this.blogService = blogService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<Page<BlogDto>> getAllBlogs(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "20") int size,
            @RequestParam(name = "searchTitle", required = false) String searchTitle,
            @RequestParam(name = "searchCategoryId", required = false) Long searchCategoryId) {
        Sort sort = Sort.by("createdAt").descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Blog> blogPage;
        if (searchTitle != null && !searchTitle.isBlank() && searchCategoryId != null) {
            blogPage = this.blogService.searchByTitleAndCategoryId(searchTitle.trim(), searchCategoryId, pageable);
        } else if (searchTitle != null && !searchTitle.isBlank()) {
            blogPage = this.blogService.searchByTitle(searchTitle.trim(), pageable);
        } else if (searchCategoryId != null) {
            blogPage = this.blogService.searchByCategoryId(searchCategoryId, pageable);
        } else {
            blogPage = this.blogService.findAll(pageable);
        }

        Page<BlogDto> blogDtoPage = blogPage.map(this::toDto);
        return new ResponseEntity<>(blogDtoPage, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogDto> getBlogById(@PathVariable(name = "id") Long id) {
        Blog blog = this.blogService.findById(id);
        if (blog == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(toDto(blog), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Blog> createBlog(@RequestBody BlogDto blogDto) {
        Blog blog = new Blog();
        BeanUtils.copyProperties(blogDto, blog);
        if (blogDto.getCategoryId() != null) {
            Category category = categoryService.findById(blogDto.getCategoryId());
            if (category == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            blog.setCategory(category);
        }
        this.blogService.save(blog);
        return new ResponseEntity<>(blog, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Blog> updateBlog(@PathVariable(name = "id") Long id, @RequestBody BlogDto blogDto) {
        Blog blog = this.blogService.findById(id);
        if (blog == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        BeanUtils.copyProperties(blogDto, blog, "id", "createdAt");
        if (blogDto.getCategoryId() != null) {
            Category category = categoryService.findById(blogDto.getCategoryId());
            if (category == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            blog.setCategory(category);
        }
        this.blogService.save(blog);
        return new ResponseEntity<>(blog, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlog(@PathVariable(name = "id") Long id) {
        Blog blog = this.blogService.findById(id);
        if (blog == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.blogService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private BlogDto toDto(Blog blog) {
        BlogDto blogDto = new BlogDto();
        BeanUtils.copyProperties(blog, blogDto);
        if (blog.getCategory() != null) {
            blogDto.setCategoryId(blog.getCategory().getId());
            blogDto.setCategoryName(blog.getCategory().getName());
        }
        return blogDto;
    }
}
