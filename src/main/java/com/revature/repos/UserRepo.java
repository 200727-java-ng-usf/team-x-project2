package com.revature.repos;

import com.revature.exceptions.AuthenticationException;
import com.revature.models.User;
import com.revature.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Optional;

public class UserRepo {
private SessionFactory factory = HibernateUtil.getSessionFactory();
private Session session = factory.getCurrentSession();

    public Optional<User> findUserByCredentials(String username, String password) {
        session.beginTransaction();
        Optional<User> user = Optional.of(session.createQuery("from users u where u.username = :un and u.password = :pw", User.class)
                .setParameter("un", username)
                .setParameter("pw", password)
                .getSingleResult());
        if (user.equals(Optional.empty())){
            throw new AuthenticationException("No User found with that username/password combination");
        }
        return user;
    }
}
