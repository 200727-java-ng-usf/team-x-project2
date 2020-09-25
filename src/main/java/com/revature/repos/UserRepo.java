package com.revature.repos;

import com.revature.exceptions.AuthenticationException;
import com.revature.exceptions.FailedTransactionException;
import com.revature.exceptions.ResourceNotFoundException;
import com.revature.models.User;

import com.revature.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
        if (user.equals(Optional.empty())){
            throw new AuthenticationException("No User found with that username/password combination");
        }
        return user;
    }

    public void register(User user){
        getSession().beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        Optional<User> userTest = findUserByCredentials(user.getUsername(), user.getPassword());
        if (!userTest.isPresent()){
            throw new FailedTransactionException("New User failed to register");
        }
    }

    public Optional<User> findUserByUsername(String username) {
        getSession().beginTransaction();
        Optional<User> user = Optional.of(session.createQuery("from users u where u.username = :un", User.class)
                .setParameter("un", username)
                .getSingleResult());
        if (user.equals(Optional.empty())){
            session.getTransaction().rollback();
            throw new ResourceNotFoundException("No User found with that username");
        }
        session.getTransaction().commit();
        return user;
    }

    public Optional<User> findUserByEmail(String email) {
        getSession().beginTransaction();
        Optional<User> user = Optional.of(session.createQuery("from users u where u.email = :em", User.class)
                .setParameter("em", email)
                .getSingleResult());
        if (user.equals(Optional.empty())){
            session.getTransaction().rollback();
            throw new ResourceNotFoundException("No User found with that email");
        }
        session.getTransaction().commit();
        return user;
    }

    public Set<User> getAllUsers(){
        getSession().beginTransaction();
        Set<User> users = new HashSet<>();
        users.addAll(session.createQuery("from users", User.class).list());
        session.getTransaction().commit();
        if (users.isEmpty()){
            throw new ResourceNotFoundException("No users present");
        }
        return users;

    }
    public Optional<User> findUserById(int id) {
        getSession().beginTransaction();
        Optional<User> user = Optional.of(session.createQuery("from users u where u.userId = :id", User.class)
                .setParameter("id", id)
                .getSingleResult());
        if (user.equals(Optional.empty())){
            session.getTransaction().rollback();
            throw new ResourceNotFoundException("No User found with that Id number");
        }
        session.getTransaction().commit();
        return user;
    }

    public void updateUser(User updatedUser){
        getSession().beginTransaction();
        session.update(updatedUser);
        session.getTransaction().commit();
        User userTest = findUserByCredentials(updatedUser.getUsername(), updatedUser.getPassword()).get();
        if (userTest.equals(updatedUser)){
            throw new FailedTransactionException("User failed to update");
        }

    }







    public void deleteUser(User deleteUser){
        getSession().beginTransaction();
        session.delete(deleteUser);
        session.getTransaction().commit();
        Optional<User> userTest = findUserById(deleteUser.getUserId());
        if (userTest.isPresent()){
            throw new FailedTransactionException("Failed to delete User");
        }
    }

    public Session getSession() {
        if (!session.getTransaction().isActive()) {
            session = factory.getCurrentSession();
        }
        return session;

    }
}
