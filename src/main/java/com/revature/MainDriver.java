package com.revature;

import com.revature.models.User;
import com.revature.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import java.util.List;

public class MainDriver {

    //test test
    //dev branch check
    //eli-paetow-f1 check


    private static SessionFactory factory = HibernateUtil.getSessionFactory();
    public static void main(String[] args) {
//        //testing out adding a new User
//        try (Session session = factory.getCurrentSession()){
//
//
//            session.beginTransaction();
//
//            User user1 = new User("TestUser", "Test", "TestFirst", "TestLast", "Test@test.com", 00000);
//
//            session.save(user1);
//            session.getTransaction().commit();
//
//            System.out.println(user1);
//        } catch (Exception e){
//            e.printStackTrace();
//        }
        // testing get all from users
//        try (Session session = factory.getCurrentSession()){
//
//
//            session.beginTransaction();
//
//            User user1 = new User("TestUser", "Test", "TestFirst", "TestLast", "Test@test.com", 00000);
//
//            List<User> allUsers = session.createQuery("from users", User.class).list();
//            for (User user: allUsers) {
//                System.out.println(user);
//            }
//            session.getTransaction().commit();
//
//        } catch (Exception e){
//            e.printStackTrace();
//        }
    }
}
