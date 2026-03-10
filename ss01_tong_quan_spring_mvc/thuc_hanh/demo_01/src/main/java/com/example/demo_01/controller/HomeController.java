package com.example.demo_01.controller;

import com.example.demo_01.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @Autowired
    IStudentService studentService;
    @RequestMapping("/home")
    public String home(Model model) {
        model.addAttribute("studentList", studentService.findAll());
        return "list";
    }
}
