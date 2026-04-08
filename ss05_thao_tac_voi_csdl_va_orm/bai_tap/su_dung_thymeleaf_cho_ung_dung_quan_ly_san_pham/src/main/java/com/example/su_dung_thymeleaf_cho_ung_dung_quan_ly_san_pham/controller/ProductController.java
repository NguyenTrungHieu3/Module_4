package com.example.su_dung_thymeleaf_cho_ung_dung_quan_ly_san_pham.controller;

import com.example.su_dung_thymeleaf_cho_ung_dung_quan_ly_san_pham.entity.Product;
import com.example.su_dung_thymeleaf_cho_ung_dung_quan_ly_san_pham.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    IProductService productService;

    @ModelAttribute("manufacturers")
    public String[] getManufacturers() {
        return new String[]{"Samsung", "Apple", "Huawei", "Oppo", "Vivo", "Xiaomi", "Nokia", "Sony", "LG"};
    }

    @GetMapping("")
    public String getHomePage(Model model){
        model.addAttribute("productList", productService.getAllProducts());
        return "/product/list";
    }

    @GetMapping("/detail")
    public String getDetailPage(Model model,
                                @RequestParam(name = "id") int id) {
        model.addAttribute("product", productService.getProductById(id));
        return "/product/detail";
    }

    @GetMapping("/add")
    public String getAddPage(Model model) {
        model.addAttribute("product", new Product());
        return "/product/add";
    }

    @GetMapping("/update/{id}")
    public String getUpdatePage(@PathVariable(name = "id")int id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "/product/update";
    }

    @GetMapping("/search")
    public String searchProduct(Model model, @RequestParam(name = "productName") String productName) {
        model.addAttribute("productList", productService.searchProductsByName(productName));
        return "/product/list";
    }

    @PostMapping("/delete")
    public String deleteProduct(@RequestParam(name = "deleteId") int id) {
        productService.deleteProductById(id);
        return "redirect:/product";
    }

    @PostMapping("/add")
    public String addProduct(Product product) {
        productService.addProduct(product);
        return "redirect:/product";
    }

    @PostMapping("/update")
    public String updateProduct(Product product) {
        productService.updateProduct(product);
        return "redirect:/product";
    }
}
