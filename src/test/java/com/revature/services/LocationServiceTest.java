package com.revature.services;

import com.revature.exceptions.InvalidRequestException;
import com.revature.models.Location;
import com.revature.models.User;
import com.revature.repos.LocationRepo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class LocationServiceTest {

    private LocationService sut;
    private LocationRepo mockLocationRepo = Mockito.mock(LocationRepo.class);
    Set<Location> mockLocations = new HashSet<>();

    private Location testLocation1;


    //setup
    @Before
    public void setup() {
        mockLocations.add(new Location(1, "testCity" , "testState" , "testCountry", "testZipcode"));

        testLocation1 = new Location(2, "testCity2" , "testState2" , "testCountry2", "testZipcode2");
    }


    //tests
    //idtests
//    @Test(expected = InvalidRequestException.class)
//    public void getInvalidUserBad() {
//        sut.findLocationById(0); // there is no user with this ID
//    }
//

    @Test
    public void getByID(){
        assertEquals(2, testLocation1.getLocationId());
    }


    //teardown
    @After
    public void tearDown() {
        sut = null;
        mockLocations.removeAll(mockLocations);
    }

}
