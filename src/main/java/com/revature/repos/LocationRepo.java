package com.revature.repos;


import com.revature.exceptions.FailedTransactionException;
import com.revature.models.Location;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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

    //find all locations
    public Set<Location> getAllLocations() {
        Session session = sessionFactory.getCurrentSession();
        Set<Location> locations = new HashSet<>(session.createQuery("from locations", Location.class).list());
        return locations;

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
        Optional<Location> location = Optional.of(session.createQuery("from locations l where l.locationZipCode = :zip", Location.class)
                .setParameter("zip", locationZipCode)
                .getSingleResult());

        return location;
    }

    //delete location by id
    public void deleteLocation(Location deleteLocation){
        Session session = sessionFactory.getCurrentSession();
        session.delete(deleteLocation);

    }

}

