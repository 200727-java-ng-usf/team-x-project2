package com.revature;

import com.revature.repos.UserRepo;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainDriver {

    //test test
    //dev branch check
    //eli-paetow-f1 check

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

        // tested findUserByCredentials, register, findUserByUserName, getAllUsers in userRepo
////        System.out.println(userRepo.findUserByCredentials("TestUser", "Test").get());
//        User newUser = new User("TestUser4", "Test", "TestFirst", "TestLast", "Test4@test.com", "00000");
//      userRepo.register(newUser);
//        System.out.println(userRepo.findUserByUsername(newUser.getUsername()));
//        Set<User> users = userRepo.getAllUsers();
//        for (User user: users) {
//            System.out.println(user);
//        }

       // System.out.println(userRepo.findUserByEmail("Test@test.com"));
       // System.out.println(userRepo.findUserById(1).get());
        //testing update
//        User updatedUser = userRepo.findUserById(6).get();
//        updatedUser.setUsername("TestUser4");
//        userRepo.updateUser(updatedUser);
//        System.out.println(userRepo.findUserById(6).get());

        //testing delete
//        User deleteUser = userRepo.findUserByUsername(newUser.getUsername()).get();
//        System.out.println(deleteUser);
//        userRepo.deleteUser(deleteUser);
//        System.out.println(userRepo.findUserByUsername(deleteUser.getUsername()).get());

        //testing find all


//        System.out.println(userRepo.findUserById(8));
//         try (AnnotationConfigApplicationContext container = new AnnotationConfigApplicationContext(AppConfig.class)){

//             UserRepo userRepo = container.getBean( UserRepo.class);
//             System.out.println(userRepo.findUserById(1));
//         } catch (Exception e){
//             e.printStackTrace();
//         }
    }
}
