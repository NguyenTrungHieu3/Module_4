package com.example.demo_thymeleaft.entity;

public class Student {
    private int id;
    private String name;
    private boolean gender;
    private String[] subjects;
    private int classId;

    public Student() {
    }

    public Student(int id, String name, boolean gender, String[] subjects, int classId) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.subjects = subjects;
        this.classId = classId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String[] getSubjects() {
        return subjects;
    }

    public void setSubjects(String[] subjects) {
        this.subjects = subjects;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }
}
