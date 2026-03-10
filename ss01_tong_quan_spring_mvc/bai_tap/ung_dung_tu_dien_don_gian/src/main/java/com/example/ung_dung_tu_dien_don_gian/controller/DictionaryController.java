package com.example.ung_dung_tu_dien_don_gian.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class DictionaryController {
    private static Map<String, String> dictionary = new HashMap<>();
    static {
        dictionary.put("hello", "xin chào");
        dictionary.put("book", "quyển sách");
        dictionary.put("computer", "máy tính");
        dictionary.put("teacher", "giáo viên");
        dictionary.put("student", "sinh viên");
    }
    @GetMapping("/home")
    public String showForm() {
        return "dictionary";
    }

    @PostMapping("search")
    public String search(
            @RequestParam(name = "word") String word,
            Model model
    ) {
        model.addAttribute("mean", dictionary.get(word.toLowerCase()));
        return "dictionary";
    }
}
