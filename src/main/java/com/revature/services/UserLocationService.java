package com.revature.services;

import com.revature.repos.UserLocationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLocationService {


    private UserLocationRepo userLocationRepo;

    @Autowired
    public UserLocationService(UserLocationRepo userLocationRepo) {
        this.userLocationRepo = userLocationRepo;
    }
}
