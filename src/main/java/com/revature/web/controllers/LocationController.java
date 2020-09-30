package com.revature.web.controllers;

import com.revature.models.Location;

import com.revature.services.LocationService;
import com.revature.web.security.Secured;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;


@RestController
@RequestMapping("/locations")
public class LocationController {

    private LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService){
        this.locationService = locationService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Location addNewLocation(@RequestBody Location newLocation){
        LocationService.addNewLocation(newLocation);
        return LocationService.findLocationByZipCode(newLocation.getLocationZipCode());
    }


    @GetMapping(produces= MediaType.APPLICATION_JSON_VALUE)
    public Set<Location> getAllLocations()
    {

        Set<Location> locations = locationService.findAllLocatins();
        return locations;
    }

    @Secured(allowedRoles = {"Admin", "User"})
    @GetMapping(value="/id/{id}")
    public Location getLocationById(@PathVariable int id){
        Location location = locationService.findLocationById(id);
        return location;
    }


}
