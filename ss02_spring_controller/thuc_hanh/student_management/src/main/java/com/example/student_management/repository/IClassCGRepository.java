package com.example.student_management.repository;

import com.example.student_management.entity.ClassCG;

import java.util.List;

public interface IClassCGRepository {
    List<ClassCG> getAllClasses();
    ClassCG getClassById(int id);
    void saveClass(ClassCG classCG);
    void deleteClass(int id);
}
