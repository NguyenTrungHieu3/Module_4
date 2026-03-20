package com.example.student_management.service;

import com.example.student_management.entity.Student;

import java.util.List;

public interface IStudentService {
    List<Student> getAllStudents();
    Student getStudentById(int id);
    void saveStudent(Student student);
    void deleteStudent(int id);
    void updateStudent(Student student);
}
