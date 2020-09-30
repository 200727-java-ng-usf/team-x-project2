package com.revature.services;

import com.revature.exceptions.GoneException;
import com.revature.models.Location;
import com.revature.models.User;
import com.revature.repos.LocationRepo;
import com.revature.repos.UserLocationRepo;
import com.revature.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.Set;

@Service
public class UserLocationService {


    private LocationRepo locationRepo;
    private UserRepo userRepo;
    private UserLocationRepo userLocationRepo;

    @Autowired
    public UserLocationService(LocationRepo locationRepo, UserRepo userRepo, UserLocationRepo userLocationRepo) {
        this.locationRepo = locationRepo;
        this.userRepo = userRepo;
        this.userLocationRepo = userLocationRepo;
    }


    @Transactional
    public Set<Location> findAllFavoriteLocations(int userId) {
        Set<Location> favoriteLocations = null;
        try {
            User user = userRepo.findUserById(userId).get();
            favoriteLocations = user.getLocations();
        } catch (NoResultException nre) {
            throw new GoneException("User not found!");
        }
        return favoriteLocations;
    }
}
