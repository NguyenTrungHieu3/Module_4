package com.example.ung_dung_hien_thi_gia_vi_voi_sandwich.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SandwichController {
    @GetMapping("/home")
    public String showForm() {
        return "condiment";
    }

    @PostMapping("save")
    public String save(
            @RequestParam(name = "condiment", required = false) String[] condiments,
            Model model
    ) {
        if (condiments == null) {
            model.addAttribute("message", "Bạn chưa chọn gia vị nào!");
        } else {
            model.addAttribute("condiments", condiments);
        }
        return "result";
    }
}
