package com.example.student_management.service;

import com.example.student_management.entity.Student;
import com.example.student_management.repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService implements IStudentService{
    @Autowired
    private IStudentRepository studentRepository;

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.getAllStudents();
    }

    @Override
    public Student getStudentById(int id) {
        return studentRepository.getStudentById(id);
    }

    @Override
    public void saveStudent(Student student) {
        studentRepository.saveStudent(student);
    }

    @Override
    public void deleteStudent(int id) {
        if (getStudentById(id) == null) {
            System.out.println("Student not found");
            return;
        } else {
            studentRepository.deleteStudent(id);
        }
    }

    @Override
    public void updateStudent(Student student) {
        if (getStudentById(student.getId()) == null) {
            System.out.println("Student not found");
            return;
        }
        studentRepository.updateStudent(student);
    }
}
