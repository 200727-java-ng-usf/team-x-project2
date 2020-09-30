package com.revature.web.controllers;

import com.revature.models.Location;

import com.revature.services.LocationService;
import com.revature.web.security.Secured;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


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
        locationService.addNewLocation(newLocation);
        return locationService.findLocationByZipCode(newLocation.getLocationZipCode());
    }

    @GetMapping(value="/zip")
    public Location getLocationByZipCode(@RequestParam String zip){
        return locationService.findLocationByZipCode(zip);
    }


    @Secured(allowedRoles = {"Admin", "User"})
    @GetMapping(value="/id/{id}")
    public Location getLocationById(@PathVariable int id){
        Location location = locationService.findLocationById(id);
        return location;
    }

}
