package com.revature.services;

import com.revature.exceptions.AuthenticationException;
import com.revature.exceptions.InvalidRequestException;
import com.revature.exceptions.ResourceNotFoundException;
import com.revature.models.User;
import com.revature.models.UserRole;
import com.revature.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;


import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {


    //get info
    //update
    //delete
    //update password
    //update home only

    private UserRepo userRepo;

    //no args constructor
    @Autowired
    public UserService(UserRepo repo) {
        System.out.println("UserService no-args constructor invoked!");
        userRepo = repo;

    }



    //validate username and password
    @Transactional
    public User authenticate(String username, String password) {
        if (username == null || username.trim().equals("") || password == null || password.trim().equals("")) {
            throw new InvalidRequestException("Invalid credential values provided!");
        }


        return userRepo.findUserByCredentials(username, password)
                .orElseThrow(AuthenticationException::new);

    }

    //find all
    @Transactional(readOnly=true)
    public Set<User> findAllUsers() {

        try {
            return userRepo.getAllUsers();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    //find by user id
    @Transactional(readOnly=true)
    public User findUserById(int id) {

        if (id <= 0) {
            throw new InvalidRequestException("Id cannot be less than or equal to zero!");
        }

        try {
            return userRepo.findUserById(id).orElseThrow(ResourceNotFoundException::new);
        } catch (Exception e) {
            throw new RuntimeException();
        }

    }

    //find by username

    @Transactional
    public User findUserByUsername(String username) {

        if (username == null || username.equals("")) {
            throw new InvalidRequestException("The provided username was null or empty!");
        }

        try {
            return userRepo.findUserByUsername(username).orElseThrow(ResourceNotFoundException::new);
        } catch (Exception e) {
            throw new RuntimeException();
        }

    }



    //register a new user
    @Transactional
    public void register(User newUser) {

        if (!isUserValid(newUser)) {
            throw new InvalidRequestException("Invalid user field values provided during registration!");
        }

        Optional<User> existingUser = userRepo.findUserByUsername(newUser.getUsername());
        if (existingUser.isPresent()) {
            throw new RuntimeException("Provided username is already in use!");
        }

        newUser.setUserRole(UserRole.USER);
        userRepo.register(newUser);

    }

    //update user
    //if user not there or not equal to new user
    //failed transaction user

    @Transactional
    public void update (User updatedUser) throws IOException {
        userRepo.updateUser(updatedUser);
        User testUser = findUserById(updatedUser.getUserId());
        if (!testUser.equals(updatedUser)){
            throw new RuntimeException("User did not update");
        }
    }


    //delete a user
    @Transactional
    public void delete(User deletedUser){
        userRepo.deleteUser(deletedUser);
        User testUser = findUserById(deletedUser.getUserId());

        if (!testUser.equals(deletedUser)){
            throw new RuntimeException("User did not delete");
        }


    }








    /**
     * Validates that the given user and its fields are valid (not null or empty strings). Does
     * not perform validation on id or role fields.
     *
     * @param user
     * @return true or false depending on if the user was valid or not
     */
    public boolean isUserValid(User user) {
        if (user == null) return false;
        if (user.getFirstName() == null || user.getFirstName().trim().equals("")) return false;
        if (user.getLastName() == null || user.getLastName().trim().equals("")) return false;
        if (user.getUsername() == null || user.getUsername().trim().equals("")) return false;
        if (user.getPassword() == null || user.getPassword().trim().equals("")) return false;
        return true;
    }


}



