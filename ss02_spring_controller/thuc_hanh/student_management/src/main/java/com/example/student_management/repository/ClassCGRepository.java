package com.example.student_management.repository;

import com.example.student_management.entity.ClassCG;
import com.example.student_management.util.ConnectionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClassCGRepository implements IClassCGRepository{
    @Override
    public List<ClassCG> getAllClasses() {
        Session session = ConnectionUtil.sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        List<ClassCG> classCGList = session.createQuery("from ClassCG", ClassCG.class).getResultList();
        transaction.commit();
        session.close();
        return classCGList;
    }

    @Override
    public ClassCG getClassById(int id) {
        Session session = ConnectionUtil.sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        ClassCG classCG = session.find(ClassCG.class, id);
        transaction.commit();
        session.close();
        return classCG;
    }

    @Override
    public void saveClass(ClassCG classCG) {
        Session session = ConnectionUtil.sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        session.save(classCG);
        transaction.commit();
        session.close();
    }

    @Override
    public void deleteClass(int id) {
        Session session = ConnectionUtil.sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        ClassCG classCG = session.find(ClassCG.class, id);
        if (classCG != null) {
            session.remove(classCG);
        }
        transaction.commit();
        session.close();
    }
}
