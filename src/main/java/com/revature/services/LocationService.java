package com.revature.services;


import com.revature.repos.LocationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationService {

    private LocationRepo locationRepo;

    @Autowired
    public LocationService(LocationRepo repo){
        System.out.println("LocationService no-args constructor invoked!");
        locationRepo = repo;
    }
}
