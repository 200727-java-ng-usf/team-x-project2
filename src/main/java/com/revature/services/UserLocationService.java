package com.revature.services;

import com.revature.exceptions.GoneException;
import com.revature.exceptions.ResourceNotFoundException;
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
            if (favoriteLocations.isEmpty()){
                throw new ResourceNotFoundException(user.getUsername() + " has no Favorite Locations");
            }
        } catch (NoResultException nre) {
            throw new GoneException("User not found!");
        }

        return favoriteLocations;
    }

    @Transactional
    public User addHomeLocation(Location home, int userId) {
        User user;
        try{
            locationRepo.findLocationByZipCode(home.getLocationZipCode()).get();
        } catch (NoResultException nre){
            locationRepo.addNewLocation(home);
        }
        try{
            user = userRepo.findUserById(userId).get();
        } catch (NoResultException nre){
            throw new GoneException("User Not found!");
        }
        home = locationRepo.findLocationByZipCode(home.getLocationZipCode()).get();
        user.setHome(home);
        userRepo.updateUser(user);
        return user;
    }

    @Transactional
    public User addFavoriteLocation(Location favorite, int userId) {
        User user;
        try{
            locationRepo.findLocationByZipCode(favorite.getLocationZipCode()).get();
        } catch (NoResultException nre){
            locationRepo.addNewLocation(favorite);
        }
        try{
            user = userRepo.findUserById(userId).get();
        } catch (NoResultException nre){
            throw new GoneException("User Not found!");
        }
        favorite = locationRepo.findLocationByZipCode(favorite.getLocationZipCode()).get();
        user.addLocations(favorite);
        userRepo.updateUser(user);
        return user;
    }
}
