package com.example.demo_thymeleaft.controller;

import com.example.demo_thymeleaft.entity.Student;
import com.example.demo_thymeleaft.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private IStudentService studentService;

    @ModelAttribute("subjects")
    public String[] getSubject(){
        return new String[]{"SQL","JAVA","PHP"};
    }

    @GetMapping("")
    public String home(ModelMap model){
        List<Student> studentList =  studentService.findAll();
        model.addAttribute("studentList", studentList);
        return "student/list";
    }

    @GetMapping("/add")
    public String add(ModelMap model){
        model.addAttribute("student", new Student());
        return "student/add";
    }

    @PostMapping("/add")
    public  String save(@ModelAttribute Student student,
                        RedirectAttributes redirectAttributes){
        studentService.add(student);
        redirectAttributes.addFlashAttribute("mess","is add success");
        return "redirect:/student";
    }

    @GetMapping("/detail")
    public String detail1(@RequestParam(name = "id")int id,
                          Model model){
        model.addAttribute("student",studentService.findById(id));
        return "student/detail";
    }

    @GetMapping("/detail/{id}")
    public String detail2(@PathVariable(name = "id")int id,
                          Model model){
        model.addAttribute("student",studentService.findById(id));
        return "student/detail";
    }
}
