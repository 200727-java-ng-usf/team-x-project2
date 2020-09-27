package com.revature.web.controllers;


import com.revature.models.User;
import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAllUsers(){
        return (List<User>) userService.findAllUsers();
    }

    @GetMapping(value="/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUserById(@PathVariable int id){
        return userService.findUserById(id);
    }

    @GetMapping(value="/search", produces=MediaType.APPLICATION_JSON_VALUE)
    public User getUserByUsername(@RequestParam String username) {
        return userService.findUserByUsername(username);
    }
}
