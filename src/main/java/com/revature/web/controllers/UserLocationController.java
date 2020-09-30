package com.revature.web.controllers;

import com.revature.dtos.Principal;
import com.revature.models.Location;
import com.revature.models.User;
import com.revature.services.UserLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@RestController
@RequestMapping("/user/location")
public class UserLocationController {

    private UserLocationService userLocationService;

    @Autowired
    public UserLocationController(UserLocationService userLocationService) {
        this.userLocationService = userLocationService;
    }

    @GetMapping(value="/favorites", produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<Location> getAllFavoriteLocations(HttpServletRequest request){
        Principal principal = (Principal) request.getSession().getAttribute("principal");
        return userLocationService.findAllFavoriteLocations(principal.getUserId());
    }

    @PutMapping(value="/home", produces = MediaType.APPLICATION_JSON_VALUE)
    public User addHomeLocationToUser(@RequestBody Location home, HttpServletRequest request){
        Principal principal = (Principal) request.getSession().getAttribute("principal");
        return userLocationService.addHomeLocation(home, principal.getUserId());
    }

    @PutMapping(value="/favorites", produces = MediaType.APPLICATION_JSON_VALUE)
    public User addFavoriteLocationToUser(@RequestBody Location favorite, HttpServletRequest request){
        Principal principal = (Principal) request.getSession().getAttribute("principal");
        return userLocationService.addFavoriteLocation(favorite, principal.getUserId());
    }

}
