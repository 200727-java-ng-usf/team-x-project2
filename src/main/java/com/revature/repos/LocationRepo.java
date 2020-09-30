package com.revature.repos;


import com.revature.models.Location;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class LocationRepo {

    private  SessionFactory sessionFactory;

    @Autowired
    public LocationRepo(SessionFactory factory) {
        sessionFactory = factory;
    }


    public void addNewLocation(Location newLocation) {
        Session session = sessionFactory.getCurrentSession();
        session.save(newLocation);
    }



    //find location by id
        public Optional<Location> findLocationById ( int id){
            Session session = sessionFactory.getCurrentSession();
            Optional<Location> location = Optional.of(session.createQuery("from locations l where l.locationId = :id", Location.class)
                    .setParameter("id", id)
                    .getSingleResult());

            return location;
        }

    public Optional<Location> findLocationByZipCode(String locationZipCode) {
        Session session = sessionFactory.getCurrentSession();
        return Optional.of(session.createQuery("from locations l where l.locationZipCode = :zip", Location.class)
                .setParameter("zip", locationZipCode)
                .getSingleResult());
    }
}
