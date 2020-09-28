package com.revature.web.controllers;


import com.revature.models.User;
import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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



    @GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
    public Set<User> getAllUsers()
    {
        System.out.println("getAllUsers initialized");
        Set<User> users = userService.findAllUsers();
        System.out.println("userService finished");
        System.out.println(users);
        return users;
    }


    @GetMapping(value="/id/{id}")
    public User getUserById(@PathVariable int id){
        User user = userService.findUserById(id);
        System.out.println(user);
        return user;
    }


    @GetMapping(value="/search/username", produces=MediaType.APPLICATION_JSON_VALUE)
    public User getUserByUsername(@RequestParam String username) {
        return userService.findUserByUsername(username);
    }

    @GetMapping(value="/search/email", produces =MediaType.APPLICATION_JSON_VALUE)
    public User getUserByEmail(@RequestParam String email){
        return userService.findUserByEmail(email);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public User registerNewUser(@RequestBody User newUser){
         userService.register(newUser);
         return userService.findUserByUsername(newUser.getUsername());
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(produces =MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public User updateUser(@RequestBody User updatedUser) throws IOException {
        userService.update(updatedUser);
        return userService.findUserByUsername(updatedUser.getUsername());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value="/delete", produces =MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteUser(@RequestParam int id){
        User userToBeDeleted = userService.findUserById(id);
        userService.delete(userToBeDeleted);
    }

}
