package com.example.ung_dung_muon_sach.controller;

import org.springframework.web.bind.annotation.RequestMapping;

public class HomeController {
    @RequestMapping("/")
    public String home() {
        return "redirect:/books";
    }

}
