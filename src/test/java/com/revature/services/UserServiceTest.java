package com.revature.services;

import com.revature.exceptions.*;
import com.revature.models.User;
import com.revature.models.UserRole;
import com.revature.repos.UserRepo;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;


import javax.persistence.NoResultException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

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
    private User testUser2;
    private User testUser3;
    private User testUser4;


    //setup
    @Before
    public void setup() {
        sut = new UserService(mockUserRepo);
        mockUsers.add(new User("Adam", "Inn", "admin", "secret", "admin@app.com", "Admin"));
        mockUsers.add(new User("Manny", "Gerr", "manager", "manage", "manager@app.com", "User"));
        mockUsers.add(new User("Alice", "Anderson", "aanderson", "password", "admin@app.com", "User"));
        mockUsers.add(new User("Bob", "Bailey", "bbailey", "dev", "dev@app.com", "Admin"));

        testUser = new User("eli", "eli1", "elimaiil");

        testUser2 = new User(1, "test", "password", "test", "test", "test", "1234", UserRole.ADMIN);

        testUser3 = new User("eli", "password", "eli@mail");

        testUser4 = new User("james", "password", "eli@mail"  );



    }

    //tests
    @Test(expected = InvalidRequestException.class)
    public void getInvalidUserBad() {
        sut.findUserById(-1);


        // there is no user with this ID
    }


    @Test
    public void getByID() {
        assertEquals(1, testUser2.getUserId());
    }




    @Test(expected = InvalidRequestException.class)
    public void authenticateWithEmptyBad() {

        sut.authenticate("", "");
    }

    @Test(expected = InvalidRequestException.class)
    public void authenticateCredentialsNull() {
        sut.authenticate(null, null);
    }


    @Test(expected = ResourceNotFoundException.class)
    public void getUserByIdThatDoesNotExist() {
        sut.findUserById(300); // user with ID 300 does not exist
    }

    @Test(expected = ResourceNotFoundException.class)
    public void getByUsernameDoesNotExist() {
        sut.findUserByUsername("garbage");
    }

    @Test
    public void getByUsername() {
        assertEquals("eli", testUser.getUsername());
    }

    @Test(expected = InvalidRequestException.class)
    public void getByUsernameNull() {
        sut.findUserByUsername(null);
    }

    @Test(expected = InvalidRequestException.class)
    public void getByUsernameEmpty() {
        sut.findUserByUsername("");
    }

    @Test(expected = InvalidRequestException.class)
    public void getByEmailDoesNotExistEmpty() {
        sut.findUserByEmail("");
    }

    @Test(expected = InvalidRequestException.class)
    public void getByEmailDoesNotExistNull() {
        sut.findUserByEmail(null);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void getByEmailDoesNotExist() {
        sut.findUserByEmail("garbageMail");
    }

    @Test
    public void getByEmail() {
        assertEquals("elimaiil", testUser.getEmail());
    }


    @Test(expected = NullPointerException.class)
    public void updateUserThatDoesNotExist() throws IOException {
        // arrange
        User nullUser = null;

        // act
        sut.update(nullUser);
    }

    @Test(expected = InvalidRequestException.class)
    public void updateUserThatDoesExists() throws IOException {
        // arrange


        // act
        sut.update(testUser);
    }


    @Test(expected = AuthenticationException.class)
    public void authenticationWithUnknownCredentials() {
        sut.authenticate("doesNotExist", "user");
    }

    @Test(expected = InvalidRequestException.class)
    public void registerWithNullUser() {

        sut.register(null);
    }

    @Test(expected = InvalidRequestException.class)
    public void registerWithSameName() {

        sut.register(testUser);
        sut.register(testUser3);
    }


//    @Test(expected = ResourceAlreadySavedException.class)
//    public void registerWithSameUserName() {
//
//        sut.register(testUser);
//        sut.register(testUser3);
//    }
//
//    @Test(expected = ResourceAlreadySavedException.class)
//    public void registerWithSameEmail() {
//
//        sut.register(testUser3);
//        sut.register(testUser4);
//    }





    @Test(expected = InvalidRequestException.class)
    public void registerWithEmptyUser() {

        testUser = new User("", "", "");

        sut.register(testUser);
    }





    //get all tests
//    @Test(expected = NullPointerException.class)
//    public void GetAllUsersBad(){
//        Mockito.when(mockUserRepo.findUserById(1)).thenThrow(NullPointerException.class);
//        mockUserRepo.getAllUsers();
//    }

    @Test
    public void getAllusers(){
        Set<User> users = new HashSet<>();
        users.add(testUser);
        Mockito.when(mockUserRepo.getAllUsers()).thenReturn(users);
        Set<User> actualResult = sut.findAllUsers();
        Assert.assertEquals(users, actualResult);

    }


    @Test(expected = NullPointerException.class)
    public void getAllUsersWithNoUsers() {
        // arrange
        sut = null;
        mockUsers.removeAll(mockUsers);

        // act
        sut.findAllUsers();
    }


    @Test(expected = InvalidRequestException.class)
    public void deleteTest() {
        sut.delete(testUser);
    }

    @Test(expected = NullPointerException.class)
    public void deleteTestNull() {
        sut.delete(null);
    }

    @Test(expected = InvalidRequestException.class)
    public void deleteTestEmpty() {

        testUser = new User("", "", "");

        sut.delete(testUser);
    }


    @Test()
    public void validateTest() {

        Assert.assertTrue("", sut.isUserValid(new User("elipaetow", "eli123", "eli@mail", "password", "User")));
    }

    @Test()
    public void validateTestNull() {

        Assert.assertFalse("", sut.isUserValid(new User(null, "eli123", "eli@mail", "password", "User")));
    }

    @Test()
    public void validateTestEmpty() {

        Assert.assertFalse("", sut.isUserValid(new User("elipaetow", "", "eli@mail", "password", "User")));
    }


    //teardown
    @After
    public void tearDown() {
        sut = null;
        mockUsers.removeAll(mockUsers);
    }
}
