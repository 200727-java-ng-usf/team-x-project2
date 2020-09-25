package com.revature.repos;

import com.revature.exceptions.AuthenticationException;
import com.revature.exceptions.FailedTransactionException;
import com.revature.exceptions.ResourceNotFoundException;
import com.revature.models.User;

import com.revature.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.NoResultException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class UserRepo {

private SessionFactory factory = HibernateUtil.getSessionFactory();
private Session session = factory.getCurrentSession();

    public Optional<User> findUserByCredentials(String username, String password) {
        getSession().beginTransaction();
        Optional<User> user = Optional.of(session.createQuery("from users u where u.username = :un and u.password = :pw", User.class)
                .setParameter("un", username)
                .setParameter("pw", password)
                .getSingleResult());
        session.getTransaction().commit();
        return user;
    }

    public void register(User user){
        getSession().beginTransaction();
        session.save(user);
        session.getTransaction().commit();
    }

    public Optional<User> findUserByUsername(String username) {
        getSession().beginTransaction();
        Optional<User> user = Optional.of(session.createQuery("from users u where u.username = :un", User.class)
                .setParameter("un", username)
                .getSingleResult());
        session.getTransaction().commit();
        return user;
    }

    public Optional<User> findUserByEmail(String email) {
        getSession().beginTransaction();
        Optional<User> user = Optional.of(session.createQuery("from users u where u.email = :em", User.class)
                .setParameter("em", email)
                .getSingleResult());
        session.getTransaction().commit();
        return user;
    }

    public Set<User> getAllUsers(){
        getSession().beginTransaction();
        Set<User> users = new HashSet<>();
        users.addAll(session.createQuery("from users", User.class).list());
        session.getTransaction().commit();
        return users;

    }
    public Optional<User> findUserById(int id) {
        getSession().beginTransaction();
        Optional<User> user = Optional.of(session.createQuery("from users u where u.userId = :id", User.class)
                       .setParameter("id", id)
                       .getSingleResult());
        session.getTransaction().commit();
        return user;
    }

    public void updateUser(User updatedUser){
        getSession().beginTransaction();
        session.update(updatedUser);
        session.getTransaction().commit();
    }







    public void deleteUser(User deleteUser){
        getSession().beginTransaction();
        session.delete(deleteUser);
        session.getTransaction().commit();
    }

    public Session getSession() {
        if (!session.getTransaction().isActive()) {
            session = factory.getCurrentSession();
        }
        return session;

    }
}
