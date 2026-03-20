package com.example.student_management.repository;

import com.example.student_management.entity.ClassCG;
import com.example.student_management.entity.Student;
import com.example.student_management.util.ConnectionUtil;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepository implements IStudentRepository{
    @Override
    public List<Student> getAllStudents() {
        Session session = ConnectionUtil.sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        TypedQuery<Student> query = session.createQuery("from Student", Student.class);
        List<Student> studentList = query.getResultList();
        transaction.commit();
        session.close();
        return studentList;
    }

    @Override
    public Student getStudentById(int id) {
        Session session = ConnectionUtil.sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        Student student = session.find(Student.class, id);
        transaction.commit();
        session.close();
        return student;
    }

    @Override
    public void saveStudent(Student student) {
        Session session = ConnectionUtil.sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        ClassCG classCG = session.find(ClassCG.class, student.getClassCG().getId());
        student.setClassCG(classCG);
        session.save(student);
        transaction.commit();
        session.close();
    }

    @Override
    public void deleteStudent(int id) {
        Session session = ConnectionUtil.sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        Student student = session.find(Student.class, id);
        if (student == null) {
            session.delete(student);
        }
        transaction.commit();
        session.close();
    }

    @Override
    public void updateStudent(Student student) {
        Session session = ConnectionUtil.sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        session.merge(student);
        transaction.commit();
        session.close();
    }
}
