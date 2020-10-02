package com.revature.web.controllers;

import com.revature.models.Location;

import com.revature.models.User;
import com.revature.services.LocationService;
import com.revature.web.security.Secured;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@RestController
@RequestMapping("/locations")
public class LocationController {

    private LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService){
        this.locationService = locationService;
    }

    @Secured(allowedRoles = {"Admin", "User"})
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public Location addNewLocation(@RequestBody Location newLocation){
        locationService.addNewLocation(newLocation);
        return locationService.findLocationByZipCode(newLocation.getLocationZipCode());
    }

    @Secured(allowedRoles = {"Admin", "User"})
    @GetMapping(value="/zip")
    @CrossOrigin
    public Location getLocationByZipCode(@RequestParam String zip){
        return locationService.findLocationByZipCode(zip);
    }

    @Secured(allowedRoles = {"Admin"})
    @GetMapping(produces= MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public Set<Location> getAllLocations()
    {

        Set<Location> locations = locationService.findAllLocatins();
        return locations;
    }

    @Secured(allowedRoles = {"Admin", "User"})
    @GetMapping(value="/id/{id}")
    @CrossOrigin
    public Location getLocationById(@PathVariable int id){
        Location location = locationService.findLocationById(id);
        return location;
    }

    @Secured(allowedRoles = {"Admin"})
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping
    @CrossOrigin
    public void deleteLocation(@RequestParam int id){
        Location locationToBeDeleted = locationService.findLocationById(id);
        locationService.delete(locationToBeDeleted);
    }


}
