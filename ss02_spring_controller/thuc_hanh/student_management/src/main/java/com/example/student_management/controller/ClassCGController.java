package com.example.student_management.controller;

import com.example.student_management.service.IClassCGService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/classes")
public class ClassCGController {
    @Autowired
    private IClassCGService classCGService;

    @GetMapping("")
    public String getAllClasses(Model model){
        model.addAttribute("classCGList", classCGService.getAllClasses());
        return "list";
    }
}
