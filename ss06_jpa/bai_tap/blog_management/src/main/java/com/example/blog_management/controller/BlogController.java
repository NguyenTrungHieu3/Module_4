package com.example.blog_management.controller;

import com.example.blog_management.entity.Blog;
import com.example.blog_management.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/blogs")
public class BlogController {
    private final BlogService blogService;
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }
    @GetMapping
    public String getAllBlogs(Model model) {
        model.addAttribute("blogs", this.blogService.findAllBlogs());
        return "/blog/list";
    }

    @GetMapping("/{id}")
    public String getBlogById(Model model, @PathVariable(name = "id") Long id) {
        model.addAttribute("blog", this.blogService.findBlogById(id));
        return "/blog/detail";
    }

    @GetMapping("/create")
    public String createBlogForm(Model model) {
        model.addAttribute("blog", new Blog());
        return "/blog/add";
    }

    @GetMapping("/update/{id}")
    public String updateBlogForm(Model model, @PathVariable(name = "id") Long id) {
        Blog updatedBlog = this.blogService.findBlogById(id);
        if (updatedBlog != null) {
            model.addAttribute("blog", updatedBlog);
            return "/blog/update";
        }
        return "redirect:/blogs";
    }

    @PostMapping
    public String createBlog(Blog blog) {
        this.blogService.saveBlog(blog);
        return "redirect:/blogs";
    }

    @PostMapping("/update")
    public String updateBlog(Blog blog) {
        this.blogService.updateBlog(blog);
        return "redirect:/blogs";
    }

    @PostMapping("/delete/{id}")
    public String deleteBlog(@PathVariable(name = "id") Long id) {
        if (this.blogService.findBlogById(id) != null) {
            this.blogService.deleteBlog(id);
        }
        return "redirect:/blogs";
    }
}
