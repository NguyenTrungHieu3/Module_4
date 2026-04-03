package com.example.project_management.controller;

import com.example.project_management.service.IProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public String listProducts(Model model) {
        model.addAttribute("products", productService.findAllProducts());
        return "list";
    }

    @GetMapping("/{id}")
    public String viewDetail(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.findProductById(id));
        return "detail";
    }
}