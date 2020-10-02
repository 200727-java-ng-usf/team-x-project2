package com.revature.web.controllers;


import com.revature.dtos.Principal;
import com.revature.models.User;
import com.revature.services.UserService;
import com.revature.web.security.Secured;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }


    @Secured(allowedRoles = {"Admin"})
    @GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public Set<User> getAllUsers()
    {

        Set<User> users = userService.findAllUsers();
        return users;
    }

    @Secured(allowedRoles = {"Admin", "User"})
    @GetMapping(value="/id/{id}")
    @CrossOrigin
    public User getUserById(@PathVariable int id){
        User user = userService.findUserById(id);
        return user;
    }
    @Secured(allowedRoles = {"Admin"})
    @GetMapping(value="/search/username", produces=MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public User getUserByUsername(@RequestParam String username) {
        return userService.findUserByUsername(username);
    }

    @Secured(allowedRoles = {"Admin"})
    @GetMapping(value="/search/email", produces =MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public User getUserByEmail(@RequestParam String email){
        return userService.findUserByEmail(email);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public User registerNewUser(@RequestBody User newUser){
         userService.register(newUser);
         return userService.findUserByUsername(newUser.getUsername());
    }
    @Secured(allowedRoles = {"Admin", "User"})
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(produces =MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public User updateUser(@RequestBody User updatedUser) throws IOException {
        userService.update(updatedUser);
        return userService.findUserByUsername(updatedUser.getUsername());
    }

    @Secured(allowedRoles = {"Admin", "User"})
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value="/password", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public User updateUserPassword(@RequestParam String password, HttpServletRequest request) throws IOException {
        Principal principal = (Principal) request.getSession().getAttribute("principal");
        User user = userService.findUserById(principal.getUserId());
        userService.updatePassword(user, password);
        return userService.findUserById(principal.getUserId());
    }

    @Secured(allowedRoles = {"Admin"})
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping
    @CrossOrigin
    public void deleteUser(@RequestParam int id){
        User userToBeDeleted = userService.findUserById(id);
        userService.delete(userToBeDeleted);
    }

}
