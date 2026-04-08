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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/blogs")
public class BlogController {
    private final BlogService blogService;
    private final CategoryService categoryService;

    public BlogController(BlogService blogService, CategoryService categoryService) {
        this.blogService = blogService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String getAllBlogs(@RequestParam(name = "page", required = false, defaultValue = "0") int page,
                              @RequestParam(name = "size", required = false, defaultValue = "10") int size,
                              @RequestParam(name = "searchTitle", required = false) String searchTitle,
                              @RequestParam(name = "searchCategoryId", required = false) Long searchCategoryId,
                              Model model) {
        Sort sort = Sort.by("createdAt").descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Blog> blogPage;
        if (searchTitle != null && !searchTitle.isEmpty() && searchCategoryId != null) {
            blogPage = this.blogService.searchByTitleAndCategoryId(searchTitle, searchCategoryId, pageable);
        } else if (searchTitle != null && !searchTitle.isEmpty()) {
            blogPage = this.blogService.searchByTitle(searchTitle, pageable);
        } else if (searchCategoryId != null) {
            blogPage = this.blogService.searchByCategoryId(searchCategoryId, pageable);
        } else {
            blogPage = this.blogService.findAll(pageable);
        }
        model.addAttribute("blogs", blogPage);
        model.addAttribute("categories", this.categoryService.findAll());
        model.addAttribute("searchTitle", searchTitle);
        model.addAttribute("searchCategoryId", searchCategoryId);
        return "/blog/list";
    }

    @GetMapping("/{id}")
    public String getBlogById(Model model, @PathVariable(name = "id") Long id) {
        Blog blog = this.blogService.findById(id);
        if (blog == null) {
            return "redirect:/blogs";
        }
        model.addAttribute("blog", blog);
        return "/blog/detail";
    }

    @GetMapping("/create")
    public String createBlogForm(Model model) {
        model.addAttribute("blogDto", new BlogDto());
        model.addAttribute("categories", this.categoryService.findAll());
        return "/blog/add";
    }

    @GetMapping("/update/{id}")
    public String updateBlogForm(Model model, @PathVariable(name = "id") Long id) {
        Blog updatedBlog = this.blogService.findById(id);
        if (updatedBlog == null) {
            return "redirect:/blogs";
        }
        BlogDto blogDto = new BlogDto();
        BeanUtils.copyProperties(updatedBlog, blogDto);
        if (updatedBlog.getCategory() != null) {
            blogDto.setCategoryId(updatedBlog.getCategory().getId());
        }
        model.addAttribute("blogDto", blogDto);
        model.addAttribute("categories", this.categoryService.findAll());
        return "/blog/update";
    }

    @PostMapping
    public String createBlog(@ModelAttribute("blogDto") BlogDto blogDto) {
        Blog blog = new Blog();
        BeanUtils.copyProperties(blogDto, blog);
        Category category = this.categoryService.findById(blogDto.getCategoryId());
        blog.setCategory(category);
        this.blogService.save(blog);
        return "redirect:/blogs";
    }

    @PostMapping("/update")
    public String updateBlog(@ModelAttribute("blogDto") BlogDto blogDto) {
        Blog blog = this.blogService.findById(blogDto.getId());
        if (blog == null) {
            return "redirect:/blogs";
        }
        BeanUtils.copyProperties(blogDto, blog);
        Category category = this.categoryService.findById(blogDto.getCategoryId());
        blog.setCategory(category);
        this.blogService.save(blog);
        return "redirect:/blogs";
    }

    @PostMapping("/delete/{id}")
    public String deleteBlog(@PathVariable(name = "id") Long id) {
        if (this.blogService.findById(id) != null) {
            this.blogService.deleteById(id);
        }
        return "redirect:/blogs";
    }
}
