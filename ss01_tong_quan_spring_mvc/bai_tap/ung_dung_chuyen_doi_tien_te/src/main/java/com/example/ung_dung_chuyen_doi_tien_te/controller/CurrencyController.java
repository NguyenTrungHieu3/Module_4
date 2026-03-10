package com.example.ung_dung_chuyen_doi_tien_te.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CurrencyController {
    @GetMapping("/home")
    public String showForm() {
        return "calculator";
    }

    @PostMapping("/convert")
    public String convert(
            @RequestParam (name = "rate") double rate,
            @RequestParam (name = "usd") double usd,
            Model model
    ) {
        double vnd = usd * rate;
        model.addAttribute("vnd", vnd);
        return "calculator";
    }
}
