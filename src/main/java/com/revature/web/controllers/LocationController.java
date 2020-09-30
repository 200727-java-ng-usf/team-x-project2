package com.revature.web.controllers;

import com.revature.models.Location;
import com.revature.models.User;
import com.revature.services.LocationService;
import com.revature.web.security.Secured;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/locations")
public class LocationController {

    private LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService){
        this.locationService = locationService;
    }


    @Secured(allowedRoles = {"Admin", "User"})
    @GetMapping(value="/id/{id}")
    public Location getLocationById(@PathVariable int id){
        Location location = locationService.findLocationById(id);
        return location;
    }


}
