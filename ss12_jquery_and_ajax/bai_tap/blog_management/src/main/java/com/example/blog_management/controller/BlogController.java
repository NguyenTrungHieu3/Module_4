package com.example.blog_management.controller;

import com.example.blog_management.dto.BlogDto;
import com.example.blog_management.entity.Blog;
import com.example.blog_management.entity.Category;
import com.example.blog_management.service.BlogService;
import com.example.blog_management.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

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
    public String showListPage(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "blog/list";
    }

    @GetMapping("/create")
    public String showCreatePage(Model model) {
        model.addAttribute("blogDto", new BlogDto());
        model.addAttribute("categories", categoryService.findAll());
        return "blog/add";
    }

    @PostMapping
    public String createBlog(@ModelAttribute("blogDto") BlogDto blogDto, RedirectAttributes redirectAttributes) {
        Blog blog = new Blog();
        BeanUtils.copyProperties(blogDto, blog);
        if (blogDto.getCategoryId() != null) {
            Category category = categoryService.findById(blogDto.getCategoryId());
            blog.setCategory(category);
        }
        blog.setCreatedAt(new Date());
        blog.setUpdatedAt(new Date());
        blogService.save(blog);
        redirectAttributes.addFlashAttribute("message", "Create blog successfully.");
        return "redirect:/blogs";
    }

    @GetMapping("/{id}")
    public String showDetailPage(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Blog blog = blogService.findById(id);
        if (blog == null) {
            redirectAttributes.addFlashAttribute("message", "Blog not found.");
            return "redirect:/blogs";
        }
        model.addAttribute("blog", blog);
        return "blog/detail";
    }

    @GetMapping("/update/{id}")
    public String showUpdatePage(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Blog blog = blogService.findById(id);
        if (blog == null) {
            redirectAttributes.addFlashAttribute("message", "Blog not found.");
            return "redirect:/blogs";
        }
        BlogDto blogDto = new BlogDto();
        BeanUtils.copyProperties(blog, blogDto);
        if (blog.getCategory() != null) {
            blogDto.setCategoryId(blog.getCategory().getId());
        }
        model.addAttribute("blogDto", blogDto);
        model.addAttribute("categories", categoryService.findAll());
        return "blog/update";
    }

    @PostMapping("/update")
    public String updateBlog(@ModelAttribute("blogDto") BlogDto blogDto, RedirectAttributes redirectAttributes) {
        Blog blog = blogService.findById(blogDto.getId());
        if (blog == null) {
            redirectAttributes.addFlashAttribute("message", "Blog not found.");
            return "redirect:/blogs";
        }

        blog.setTitle(blogDto.getTitle());
        blog.setAuthor(blogDto.getAuthor());
        blog.setSummary(blogDto.getSummary());
        blog.setContent(blogDto.getContent());
        blog.setImageUrl(blogDto.getImageUrl());
        if (blogDto.getCategoryId() != null) {
            blog.setCategory(categoryService.findById(blogDto.getCategoryId()));
        }
        blog.setUpdatedAt(new Date());

        blogService.save(blog);
        redirectAttributes.addFlashAttribute("message", "Update blog successfully.");
        return "redirect:/blogs";
    }

    @PostMapping("/delete/{id}")
    public String deleteBlog(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Blog blog = blogService.findById(id);
        if (blog != null) {
            blogService.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "Delete blog successfully.");
        }
        return "redirect:/blogs";
    }
}

