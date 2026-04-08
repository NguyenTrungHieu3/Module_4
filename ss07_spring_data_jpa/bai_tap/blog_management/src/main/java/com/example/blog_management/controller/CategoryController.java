package com.example.blog_management.controller;

import com.example.blog_management.entity.Category;
import com.example.blog_management.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String getAllCategories(Model model) {
        model.addAttribute("categories", this.categoryService.findAll());
        return "/category/list";
    }

    @GetMapping("/create")
    public String createCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "/category/add";
    }

    @PostMapping
    public String createCategory(@ModelAttribute("category") Category category) {
        this.categoryService.save(category);
        return "redirect:/categories";
    }

    @GetMapping("/update/{id}")
    public String updateCategoryForm(@PathVariable(name = "id") Long id, Model model) {
        Category category = this.categoryService.findById(id);
        if (category == null) {
            return "redirect:/categories";
        }
        model.addAttribute("category", category);
        return "/category/update";
    }

    @PostMapping("/update")
    public String updateCategory(@ModelAttribute("category") Category category) {
        if (category.getId() == null || this.categoryService.findById(category.getId()) == null) {
            return "redirect:/categories";
        }
        this.categoryService.save(category);
        return "redirect:/categories";
    }

    @PostMapping("/delete/{id}")
    public String deleteCategory(@PathVariable(name = "id") Long id) {
        if (this.categoryService.findById(id) != null) {
            this.categoryService.deleteById(id);
        }
        return "redirect:/categories";
    }
}
