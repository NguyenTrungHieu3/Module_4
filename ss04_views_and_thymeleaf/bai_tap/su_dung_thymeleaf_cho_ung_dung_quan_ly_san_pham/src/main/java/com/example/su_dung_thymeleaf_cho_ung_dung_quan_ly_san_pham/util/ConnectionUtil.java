package com.example.su_dung_thymeleaf_cho_ung_dung_quan_ly_san_pham.util;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class ConnectionUtil {
    public static SessionFactory sessionFactory;
    static {
        try {
            sessionFactory = new Configuration().configure("hibernate.conf.xml").buildSessionFactory();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}