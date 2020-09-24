package com.revature;

import com.revature.models.Location;
import com.revature.models.User;
import com.revature.repos.UserRepo;
import com.revature.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
//            User user1 = new User("TestUser", "Test", "TestFirst", "TestLast", "Test@test.com", "00000");
//
//            session.save(user1);
//            session.getTransaction().commit();
//
//            System.out.println(user1);
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//        // testing get all from users
//        try (Session session = factory.getCurrentSession()){
//
//
//            session.beginTransaction();
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
//        //testing location
//        try (Session session = factory.getCurrentSession()){
//
//
//            session.beginTransaction();
//
//            Location location = new Location("Raritan", "New Jersey", "US", "08869");
//
//            session.save(location);
//            session.getTransaction().commit();
//            System.out.println(location);
//
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//        //get all locations testing
//        try (Session session = factory.getCurrentSession()){
//
//
//            session.beginTransaction();
//
//
//            List<Location> allLocations = session.createQuery("from locations",Location.class).list();
//            for (Location location: allLocations) {
//                System.out.println(location);
//            }
//            session.getTransaction().commit();
//
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//      //tested home/user locations
//        try (Session session = factory.getCurrentSession()){
//
//
//            session.beginTransaction();
//
//
//            Location location = session.createQuery("from locations l where l.locationZipCode = : zc",Location.class)
//                    .setParameter("zc", "08869")
//                    .getSingleResult();
//            System.out.println(location);
//            User user = session.createQuery("from users u where u.username = :un", User.class)
//                    .setParameter("un", "TestUser")
//                    .getSingleResult();
//            user.setHome(location);
//            System.out.println(user);
//            session.update(user);
//
//            session.getTransaction().commit();
//
//        } catch (Exception e){
//            e.printStackTrace();
//        }

        // tested findUserByCredentials in userRepo
//        UserRepo userRepo = new UserRepo();
//        System.out.println(userRepo.findUserByCredentials("TestUser", "Test").get());
    }
}
