package com.example.demo_thymeleaft.service;

import com.example.demo_thymeleaft.entity.Student;

import java.util.List;

public interface IStudentService {
    List<Student> findAll();
    void add(Student student);
    Student findById(int id);
}
