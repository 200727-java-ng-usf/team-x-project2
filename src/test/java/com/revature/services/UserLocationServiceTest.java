package com.revature.services;

import com.revature.exceptions.GoneException;
import com.revature.models.Location;
import com.revature.models.User;
import com.revature.models.UserRole;
import com.revature.repos.LocationRepo;
import com.revature.repos.UserLocationRepo;
import com.revature.repos.UserRepo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.persistence.NoResultException;
import java.util.Optional;
import java.util.Set;

public class UserLocationServiceTest {

    private UserLocationService userLocationService;
    private UserRepo userRepo = Mockito.mock(UserRepo.class);
    private LocationRepo locationRepo = Mockito.mock(LocationRepo.class);
    private UserLocationRepo userLocationRepo = Mockito.mock(UserLocationRepo.class);
    private User testUser;
    private Location testLocation;

    @Before
    public void setUp(){
        userLocationService = new UserLocationService(locationRepo, userRepo, userLocationRepo);
        testLocation = new Location(1, "TestCity", "TestState", "TestCountry", "00000");
        testUser = new User(1, "Test", "Test", "Test", "Test", "Test@email.com", "00000", UserRole.USER);
        testUser.addLocations(testLocation);
    }

    @Test(expected = GoneException.class)
    public void missingUserGetAllFavoriteLocations(){
        Mockito.when(userRepo.findUserById(1)).thenThrow(NoResultException.class);
        userLocationService.findAllFavoriteLocations(1);
    }

    @Test
    public void GetAllFavoriteLocationsTrue(){
        Mockito.when(userRepo.findUserById(1)).thenReturn(Optional.of(testUser));
        Set<Location> expectedLocations = testUser.getLocations();
        Set<Location> actualLocations = userLocationService.findAllFavoriteLocations(1);
        Assert.assertEquals(expectedLocations, actualLocations);
    }

    @Test(expected = GoneException.class)
    public void addHomeLocationGoneException() {
        Mockito.when(locationRepo.findLocationByZipCode(testLocation.getLocationZipCode())).thenReturn(Optional.of(testLocation));
        Mockito.when(userRepo.findUserById(1)).thenThrow(NoResultException.class);
        userLocationService.addHomeLocation(testLocation, 1);
    }

    @Test
    public void addHomeLocationTrue() {
        Mockito.when(locationRepo.findLocationByZipCode(testLocation.getLocationZipCode())).thenReturn(Optional.of(testLocation));
        Mockito.when(userRepo.findUserById(1)).thenReturn(Optional.of(testUser));
        User actualUser = userLocationService.addHomeLocation(testLocation, 1);
        testUser.setHome(testLocation);
        Assert.assertEquals(testUser, actualUser);
    }
}
