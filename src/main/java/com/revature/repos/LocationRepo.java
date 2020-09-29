package com.revature.repos;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LocationRepo {

    private SessionFactory sessionFactory;

    @Autowired
    public LocationRepo(SessionFactory factory) {
        sessionFactory = factory;
    }
}
