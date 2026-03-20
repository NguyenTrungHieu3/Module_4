package com.example.student_management.controller;

import com.example.student_management.entity.Student;
import com.example.student_management.service.IClassCGService;
import com.example.student_management.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private IStudentService studentService;
    @Autowired
    private IClassCGService classCGService;

    @ModelAttribute("languages")
    public String[] getLanguages(){
        return new String[]{"English", "Vietnamese", "Korean"};
    }

    @GetMapping("")
    public String getAllStudents(Model model){
        model.addAttribute("studentList", studentService.getAllStudents());
        model.addAttribute("classCGList", classCGService.getAllClasses());
        return "/student/list";
    }

    @GetMapping("/add")
    public String createStudent(Model model){
        model.addAttribute("student", new Student());
        model.addAttribute("classCGList", classCGService.getAllClasses());
        return "/student/add";
    }

    @GetMapping("/edit/{id}")
    public String editStudent(@PathVariable("id") int id, Model model) {
        model.addAttribute("student", studentService.getStudentById(id));
        model.addAttribute("classCGList", classCGService.getAllClasses());
        return "/student/edit";
    }

    @PostMapping("/add")
    public String saveStudent(@ModelAttribute Student student,
                              RedirectAttributes redirectAttributes){
        studentService.saveStudent(student);
        redirectAttributes.addFlashAttribute("message", "Student added successfully");
        return "redirect:/student";
    }

    @PostMapping("/delete")
    public String deleteStudent(@RequestParam(name = "studentId") int id, RedirectAttributes redirectAttributes){
        studentService.deleteStudent(id);
        redirectAttributes.addFlashAttribute("message", "Student deleted successfully");
        return "redirect:/student";
    }

    @PostMapping("/edit")
    public String editStudent(@ModelAttribute Student student, RedirectAttributes redirectAttributes){
        studentService.updateStudent(student);
        return "redirect:/student";
    }
}
