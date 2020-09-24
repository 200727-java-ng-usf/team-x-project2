package com.revature.repos;

import com.revature.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Optional;

public class UserRepo {

    private SessionFactory sessionFactory;

    public UserRepo(SessionFactory factory){
        sessionFactory = factory;
    }


    public Optional<User> findUserByCredentials(String username, String password) {
        Session session = sessionFactory.getCurrentSession();
        return Optional.of(session.createQuery("from User u where u.username = :un and u.password = :pw", User.class)
                .setParameter("un", username)
                .setParameter("pw", password)
                .getSingleResult());
    }

    public Optional<User> save(User newObj) {
        Session session = sessionFactory.getCurrentSession();
        session.save(newObj);
        return Optional.of(newObj);
    }

    public Optional<User> findUserByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();
        return Optional.of(session.createQuery("from User u where u.username = :un", User.class)
                .setParameter("un", username)
                .getSingleResult());
    }

    public void updateUser(User updatedUser) {

    }
}
