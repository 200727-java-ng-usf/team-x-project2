package com.revature.repos;


import com.revature.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Repository
public class UserRepo {
private SessionFactory sessionFactory;

    @Autowired
    public UserRepo(SessionFactory factory) {
        sessionFactory = factory;
    }

    public Optional<User> findUserByCredentials(String username, String password) {
        Session session = sessionFactory.getCurrentSession();
        Optional<User> user = Optional.of(session.createQuery("from users u where u.username = :un and u.password = :pw", User.class)
                .setParameter("un", username)
                .setParameter("pw", password)
                .getSingleResult());
        session.getTransaction().commit();
        return user;
    }

    public void register(User user){
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
        session.getTransaction().commit();
    }

    public Optional<User> findUserByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();
        Optional<User> user = Optional.of(session.createQuery("from users u where u.username = :un", User.class)
                .setParameter("un", username)
                .getSingleResult());
        session.getTransaction().commit();
        return user;
    }

    public Optional<User> findUserByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        Optional<User> user = Optional.of(session.createQuery("from users u where u.email = :em", User.class)
                .setParameter("em", email)
                .getSingleResult());
        session.getTransaction().commit();
        return user;
    }

    public Set<User> getAllUsers(){
        Session session = sessionFactory.getCurrentSession();
        Set<User> users = new HashSet<>();
        users.addAll(session.createQuery("from users", User.class).list());
        session.getTransaction().commit();
        return users;

    }
    public Optional<User> findUserById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Optional<User> user = Optional.of(session.createQuery("from users u where u.userId = :id", User.class)
                       .setParameter("id", id)
                       .getSingleResult());
        session.getTransaction().commit();
        return user;
    }

    public void updateUser(User updatedUser){
        Session session = sessionFactory.getCurrentSession();
        session.update(updatedUser);
        session.getTransaction().commit();
    }

    public void deleteUser(User deleteUser){
        Session session = sessionFactory.getCurrentSession();
        session.delete(deleteUser);
        session.getTransaction().commit();
    }


}
