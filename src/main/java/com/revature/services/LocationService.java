package com.revature.services;




import com.revature.exceptions.InvalidRequestException;
import com.revature.exceptions.ResourceNotFoundException;
import com.revature.models.Location;
import com.revature.repos.LocationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LocationService {

    private LocationRepo locationRepo;

    @Autowired
    public LocationService(LocationRepo repo){
        System.out.println("LocationService no-args constructor invoked!");
        locationRepo = repo;
    }


    public static void addNewLocation(Location newLocation) {
        LocationRepo.addNewLocation(newLocation);
        findLocationByZipCode(newLocation.getLocationZipCode());
    }

    public static Location findLocationByZipCode(String locationZipCode) {
        return null;
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
        } catch (Exception e) {
            throw new ResourceNotFoundException();
        }

    }

}
