package com.revature.util;

import com.revature.models.User;
import com.revature.models.UserRole;
import org.hibernate.SessionFactory;
import org.reflections.Reflections;

import org.hibernate.cfg.Configuration;

import javax.persistence.Entity;
import java.util.Set;


public class HibernateUtil {

//    private static SessionFactory sessionFactory;
//
//    private static SessionFactory buildSessionFactory() {
//
//        try {
//
//            Configuration config = new Configuration();
//
//            config.configure("hibernate.cfg.xml");
//            assignAnnotatedClasses(config);
//
//
//            return config.buildSessionFactory();
//        } catch (Exception e){
//            e.printStackTrace();
//            throw new ExceptionInInitializerError(e);
//        }
//    }
//
//    public static SessionFactory getSessionFactory() {
//        return (sessionFactory == null) ? sessionFactory = buildSessionFactory() : sessionFactory;
//    }
//
//    public static void assignAnnotatedClasses(Configuration config) {
//
//        Reflections reflect = new Reflections("com.revature.models");
//        Set<Class<? extends  Object>> entities = reflect.getTypesAnnotatedWith(Entity.class);
//        entities.forEach(config::addAnnotatedClass);
//    }

}
