package com.example.demo_thymeleaft.service;

import com.example.demo_thymeleaft.entity.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService implements IStudentService{
    private static List<Student> studentList = new ArrayList<>();
    static {
        studentList.add(new Student(1, "Nguyen Van A",true, List.of("JS", "JAVA", "SQL").toArray(new String[0]),1));
        studentList.add(new Student(2, "Nguyen Van B",true, List.of("JS", "JAVA", "SQL").toArray(new String[0]),1));
    }

    @Override
    public List<Student> findAll() {
        return studentList;
    }

    @Override
    public void add(Student student) {
        studentList.add(student);
    }

    @Override
    public Student findById(int id) {
        for (int i = 0; i <studentList.size() ; i++) {
            if (id ==  studentList.get(i).getId()){
                return studentList.get(i);
            }
        }
        return null;
    }
}
