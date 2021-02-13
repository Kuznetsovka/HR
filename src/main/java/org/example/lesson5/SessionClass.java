package org.example.lesson5;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionClass {
    public static SessionFactory sessionFactory(){
        return  new Configuration().configure().buildSessionFactory();
    }
}
