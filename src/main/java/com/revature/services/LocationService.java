package com.revature.services;




import com.revature.exceptions.FailedTransactionException;
import com.revature.exceptions.InvalidRequestException;
import com.revature.exceptions.ResourceAlreadySavedException;
import com.revature.exceptions.ResourceNotFoundException;
import com.revature.models.Location;
import com.revature.repos.LocationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.Optional;

@Service
public class LocationService {

    private LocationRepo locationRepo;

    @Autowired
    public LocationService(LocationRepo repo){
        System.out.println("LocationService no-args constructor invoked!");
        locationRepo = repo;
    }

    @Transactional
    public void addNewLocation(Location newLocation) {
        try {
            Optional<Location> location =locationRepo.findLocationByZipCode(newLocation.getLocationZipCode());
            if (location.isPresent()){
                throw new ResourceAlreadySavedException("Location is already in database, no need to add it again");
            }
        } catch (NoResultException nre){
            locationRepo.addNewLocation(newLocation);
        }


    }

    @Transactional
    public Location findLocationByZipCode(String locationZipCode) {
        Location location;
        try {
            location = locationRepo.findLocationByZipCode(locationZipCode).get();
        } catch (NoResultException nre){
            throw new ResourceNotFoundException("No Location with the zip code " + locationZipCode + " was found in the database");
        }
        return location;
    }

    //find location by id
    @Transactional(readOnly=true)
    public Location findLocationById(int id) {

        if (id <= 0) {
            throw new InvalidRequestException("Id cannot be less than or equal to zero!");
        }

        try {
            Location location = locationRepo.findLocationById(id).orElseThrow(ResourceNotFoundException::new);
            return location;
        } catch (NoResultException nre) {
            throw new ResourceNotFoundException("No Location with the id " + id + " was found in the database");
        }

    }

}
