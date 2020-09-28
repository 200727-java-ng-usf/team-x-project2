package com.revature.services;

import com.revature.exceptions.AuthenticationException;
import com.revature.exceptions.InvalidRequestException;
import com.revature.exceptions.ResourceNotFoundException;
import com.revature.models.User;
import com.revature.models.UserRole;
import com.revature.repos.UserRepo;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;


import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class UserServiceTest {


    //update
    //register
    //findall
    //authenticate
    //findbyid

    //findbyusername
    //delete
    //isuservalid

    private UserService sut;
    private UserRepo mockUserRepo = Mockito.mock(UserRepo.class);
    Set<User> mockUsers = new HashSet<>();
    private User testUser;



    //setup
    @Before
    public void setup() {
        sut = new UserService(mockUserRepo);
        mockUsers.add(new User("Adam", "Inn", "admin", "secret", "admin@app.com", "Admin"));
        mockUsers.add(new User("Manny", "Gerr", "manager", "manage", "manager@app.com", "User"));
        mockUsers.add(new User("Alice", "Anderson", "aanderson", "password", "admin@app.com", "User"));
        mockUsers.add(new User("Bob", "Bailey", "bbailey", "dev", "dev@app.com", "Admin"));

        testUser = new User("eli" , "eli1" , "elimaiil");
    }

    //tests
    @Test(expected = InvalidRequestException.class)
    public void getInvalidUserBad() {
        sut.findUserById(-1); // there is no user with this ID
    }


    @Test(expected = InvalidRequestException.class)
    public void authenticateWithEmptyBad() {

        sut.authenticate("", "");
    }

    @Test(expected = RuntimeException.class)
    public void getUserByIdThatDoesNotExist() {
        sut.findUserById(300); // user with ID 300 does not exist
    }

    @Test(expected = RuntimeException.class)
    public void getByUsernameDoesNotExist(){
        sut.findUserByUsername("garbage");
    }



    @Test(expected = NullPointerException.class)
    public void updateUserThatDoesNotExist() throws IOException {
        // arrange
        User nullUser = null;

        // act
        sut.update(nullUser);
    }


    @Test(expected = AuthenticationException.class)
    public void authenticationWithUnknownCredentials() {
        sut.authenticate("doesNotExist", "user");
    }

    @Test(expected = InvalidRequestException.class)
    public void registerWithNullErsUser() {

        sut.register(null);
    }

    @Test(expected = NullPointerException.class)
    public void getAllUsersWithNoUsers() {
        // arrange
        sut = null;
        mockUsers.removeAll(mockUsers);

        // act
        sut.findAllUsers();
    }

//    @Test(expected = InvalidRequestException.class)
//    public void deleteTest(){
//        sut.delete(testUser);
//    }

    @Test()
    public void validateTest(){

        Assert.assertTrue("",sut.isUserValid(new User("elipaetow","eli123","eli@mail","password","User")));
    }


    //teardown
    @After
    public void tearDown() {
        sut = null;
        mockUsers.removeAll(mockUsers);
    }
}
