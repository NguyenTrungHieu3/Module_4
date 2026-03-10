package com.example.demo_01.service;

import com.example.demo_01.entity.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService implements IStudentService{
    private static List<Student> students = new ArrayList<>();
    static {
        students.add(new Student(1, "Trung Hiếu"));
        students.add(new Student(2, "Văn Hiếu"));
        students.add(new Student(3, "Trường"));
        students.add(new Student(4, "Thư"));
        students.add(new Student(5, "Hào"));
    }
    @Override
    public List<Student> findAll() {
        return students;
    }
}
