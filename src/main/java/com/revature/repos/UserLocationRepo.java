package com.revature.repos;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserLocationRepo {

    private SessionFactory sessionFactory;

    @Autowired
    public UserLocationRepo(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
