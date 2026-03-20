package com.example.student_management.service;

import com.example.student_management.entity.ClassCG;

import java.util.List;

public interface IClassCGService {
    List<ClassCG> getAllClasses();
    ClassCG getClassById(int id);
    void saveClass(ClassCG classCG);
    void deleteClass(int id);
}
