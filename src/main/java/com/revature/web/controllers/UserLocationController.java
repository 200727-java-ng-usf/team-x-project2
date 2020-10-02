package com.revature.web.controllers;

import com.revature.dtos.Principal;
import com.revature.models.Location;
import com.revature.models.User;
import com.revature.services.UserLocationService;
import com.revature.web.security.Secured;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Set;

@RestController
@RequestMapping("/user/location")
public class UserLocationController {

    private UserLocationService userLocationService;

    @Autowired
    public UserLocationController(UserLocationService userLocationService) {
        this.userLocationService = userLocationService;
    }

   // @Secured(allowedRoles = {"Admin", "User"})
    @GetMapping(value="/favorites/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public Set<Location> getAllFavoriteLocations(@PathVariable int id){
  
        return userLocationService.findAllFavoriteLocations(id);
    }

   // @Secured(allowedRoles = {"Admin", "User"})
    @PutMapping(value="/home/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public Principal addHomeLocationToUser(@RequestBody Location home, @PathVariable int id, HttpServletRequest req){
        User user = userLocationService.addHomeLocation(home, id);
        Principal principal = new Principal(user.getUserId(), user.getUsername(), user.getUserRole(), user.getHome());
        HttpSession userSession = req.getSession();
        userSession.setAttribute("principal", principal);
        return principal;

    }

   // @Secured(allowedRoles = {"Admin", "User"})
    @PutMapping(value="/favorites/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public User addFavoriteLocationToUser(@RequestBody Location favorite, @PathVariable int id){
        return userLocationService.addFavoriteLocation(favorite, id);
    }

}
