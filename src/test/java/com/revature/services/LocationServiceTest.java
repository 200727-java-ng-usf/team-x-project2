package com.revature.services;

import com.revature.exceptions.InvalidRequestException;
import com.revature.exceptions.ResourceAlreadySavedException;
import com.revature.exceptions.ResourceNotFoundException;
import com.revature.models.Location;
import com.revature.models.User;
import com.revature.repos.LocationRepo;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.persistence.NoResultException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class LocationServiceTest {

    private LocationService sut;
    private LocationRepo locationRepo = Mockito.mock(LocationRepo.class);
    Set<Location> mockLocations = new HashSet<>();

    private Location testLocation1;


    //setup
    @Before
    public void setup() {
        mockLocations.add(new Location(1, "testCity" , "testState" , "testCountry", "testZipcode"));

        testLocation1 = new Location(2, "testCity2" , "testState2" , "testCountry2", "testZipcode2");
        sut = new LocationService(locationRepo);
    }


    //tests
    //idtests
    //why though
    @Test(expected = InvalidRequestException.class)
    public void getInvalidUserBad() {
        sut.findLocationById(0); // there is no user with this ID
    }


    @Test
    public void getByID(){
        assertEquals(2, testLocation1.getLocationId());
    }

    @Test(expected = ResourceAlreadySavedException.class)
    public void addNewLocationException(){
        Mockito.when(locationRepo.findLocationByZipCode(testLocation1.getLocationZipCode())).thenReturn(Optional.of(testLocation1));
        sut.addNewLocation(testLocation1);
    }

    @Test
    public void addNewLocationTrue(){
        Mockito.when(locationRepo.findLocationByZipCode(testLocation1.getLocationZipCode())).thenThrow(NoResultException.class);
        sut.addNewLocation(testLocation1);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void findLocationByZipCodeException(){
        String zipCode = testLocation1.getLocationZipCode();
        Mockito.when(locationRepo.findLocationByZipCode(zipCode)).thenThrow(NoResultException.class);
        sut.findLocationByZipCode(zipCode);
    }

    @Test
    public void findLocationByZipCodeTrue(){
        Mockito.when(locationRepo.findLocationByZipCode(testLocation1.getLocationZipCode())).thenReturn(Optional.of(testLocation1));
        Assert.assertEquals(testLocation1, sut.findLocationByZipCode(testLocation1.getLocationZipCode()));
    }

    //teardown
    @After
    public void tearDown() {
        sut = null;
        mockLocations.removeAll(mockLocations);
    }

}
