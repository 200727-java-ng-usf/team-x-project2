package com.revature.services;

import com.revature.exceptions.AuthenticationException;
import com.revature.exceptions.InvalidRequestException;
import com.revature.models.User;
import com.revature.repos.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    //login
    //get info
    //register
    //update
    //delete
    //update password
    //update home only

    private UserRepo userRepo = new UserRepo();

    //no args constructor
    public UserService() {
        System.out.println("UserService no-args constructor invoked!");
    }

    //validate username and password
    public User authenticate(String username, String password) {
        if (username == null || username.trim().equals("") || password == null || password.trim().equals("")) {
            throw new InvalidRequestException("Invalid credential values provided!");
        }

        return userRepo.findUserByCredentials(username, password)
                .orElseThrow(AuthenticationException::new);

    }
}



