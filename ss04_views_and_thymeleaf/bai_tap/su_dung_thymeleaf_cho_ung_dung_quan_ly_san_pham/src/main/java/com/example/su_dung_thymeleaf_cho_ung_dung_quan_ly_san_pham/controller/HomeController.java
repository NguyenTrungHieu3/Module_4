package com.example.su_dung_thymeleaf_cho_ung_dung_quan_ly_san_pham.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String getHomePage(){
        return "redirect:/product";
    }
}
