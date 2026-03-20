package com.example.student_management.service;

import com.example.student_management.entity.ClassCG;
import com.example.student_management.repository.IClassCGRepository;
import com.example.student_management.repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClassCGService implements IClassCGService{
    @Autowired
    private IClassCGRepository classCGRepository;
    @Override
    public List<ClassCG> getAllClasses() {
        return classCGRepository.getAllClasses();
    }

    @Override
    public ClassCG getClassById(int id) {
        return classCGRepository.getClassById(id);
    }

    @Override
    public void saveClass(ClassCG classCG) {
        classCGRepository.saveClass(classCG);
    }

    @Override
    public void deleteClass(int id) {
        if (getClassById(id) == null) {
            System.out.println("Class not found");
            return;
        } else {
            classCGRepository.deleteClass(id);
        }
    }
}
