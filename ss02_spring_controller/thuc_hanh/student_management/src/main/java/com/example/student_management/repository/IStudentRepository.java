package com.example.student_management.repository;

import com.example.student_management.entity.Student;

import java.util.List;

public interface IStudentRepository {
    List<Student> getAllStudents();
    Student getStudentById(int id);
    void saveStudent(Student student);
    void deleteStudent(int id);
    void updateStudent(Student student);
}
