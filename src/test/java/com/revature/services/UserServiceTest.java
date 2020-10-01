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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {




    private UserService sut;
    private UserRepo userRepo = Mockito.mock(UserRepo.class);
    Set<User> mockUsers = new HashSet<>();
    private User testUser;
    private User testUser2;
    private User testUser3;
    private User testUser4;
    private User testUser5;
    private User testUser6;


    //setup
    @Before
    public void setup() {
        sut = new UserService(userRepo);
        mockUsers.add(new User("Adam", "Inn", "admin", "secret", "admin@app.com", "Admin"));
        mockUsers.add(new User("Manny", "Gerr", "manager", "manage", "manager@app.com", "User"));
        mockUsers.add(new User("Alice", "Anderson", "aanderson", "password", "admin@app.com", "User"));
        mockUsers.add(new User("Bob", "Bailey", "bbailey", "dev", "dev@app.com", "Admin"));

        testUser = new User("eli", "eli1", "elimaiil");

        testUser2 = new User(1, "test", "password", "test", "test", "test", "1234", UserRole.ADMIN);

        testUser3 = new User("eli", "password", "eli@mail");

        testUser4 = new User("james", "password", "eli@mail"  );



    }

    @Test(expected = AuthenticationException.class)
    public void authenticationWithUnknownCredentials() {
        Mockito.when(userRepo.findUserByCredentials("doesNotExist", "user")).thenThrow(NoResultException.class);
        sut.authenticate("doesNotExist", "user");
    }

    @Test(expected = InvalidRequestException.class)
    public void authenticationWithNullCredentials(){
        sut.authenticate(null, null);
    }

    @Test(expected = InvalidRequestException.class)
    public void authenticationWithEmptyCredentials(){
        sut.authenticate("","");
    }

    @Test
    public void authenticateTrue(){
        Mockito.when(userRepo.findUserByCredentials(testUser.getUsername(), testUser.getPassword())).thenReturn(Optional.of(testUser));
        Assert.assertEquals(testUser, sut.authenticate(testUser.getUsername(), testUser.getPassword()));
    }




    @Test(expected = InvalidRequestException.class)
    public void authenticateWithEmptyBad() {

        sut.authenticate("", "");
    }

    @Test(expected = InvalidRequestException.class)
    public void authenticateCredentialsNull() {
        sut.authenticate(null, null);
    }

    @Test
    public void getAllusers(){
        Set<User> users = new HashSet<>();
        users.add(testUser);
        Mockito.when(userRepo.getAllUsers()).thenReturn(users);
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

    //tests
    @Test(expected = InvalidRequestException.class)
    public void getInvalidUserBad() {
        sut.findUserById(-1);
        // there is no user with this ID
    }

    @Test(expected = ResourceNotFoundException.class)
    public void getUserByIdThatDoesNotExist() {
        Mockito.when(userRepo.findUserById(300)).thenThrow(NoResultException.class);
        sut.findUserById(300); // user with ID 300 does not exist
    }

    @Test
    public void getUserByIdTrue(){
        Mockito.when(userRepo.findUserById(1)).thenReturn(Optional.of(testUser2));
        Assert.assertEquals(testUser2, sut.findUserById(1));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void getByUsernameDoesNotExist() {
        Mockito.when(userRepo.findUserByUsername("garbage")).thenThrow(NoResultException.class);
        sut.findUserByUsername("garbage");
    }

    @Test
    public void getByUsername() {
        Mockito.when(userRepo.findUserByUsername("eli")).thenReturn(Optional.of(testUser));
        assertEquals(testUser, sut.findUserByUsername("eli"));
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
        Mockito.when(userRepo.findUserByEmail("garbageMail")).thenThrow(NoResultException.class);
        sut.findUserByEmail("garbageMail");
    }

    @Test
    public void getByEmail() {
        Mockito.when(userRepo.findUserByEmail("elimaiil")).thenReturn(Optional.of(testUser));
        assertEquals(testUser, sut.findUserByEmail("elimaiil"));
    }


    @Test(expected = InvalidRequestException.class)
    public void registerWithNullUser() {

        sut.register(null);
    }

    @Test(expected = ResourceAlreadySavedException.class)
    public void registerWithSameEmail() {
        Mockito.when(userRepo.findUserByEmail(testUser2.getEmail())).thenReturn(Optional.of(testUser2));
        sut.register(testUser2);
    }
    @Test(expected = ResourceAlreadySavedException.class)
    public void registerWithSameUsername(){
        Mockito.when(userRepo.findUserByEmail(testUser2.getEmail())).thenThrow(NoResultException.class);
        Mockito.when(userRepo.findUserByUsername(testUser2.getUsername())).thenReturn(Optional.of(testUser2));
        sut.register(testUser2);
    }







    @Test(expected = InvalidRequestException.class)
    public void registerWithEmptyUser() {

        testUser = new User("", "", "");

        sut.register(testUser);
    }







    @Test
    public void registerTrue(){
        Mockito.when(userRepo.findUserByEmail(testUser2.getEmail())).thenThrow(NoResultException.class);
        Mockito.when(userRepo.findUserByUsername(testUser2.getUsername())).thenThrow(NoResultException.class);
        sut.register(testUser2);
    }

    @Test(expected = ResourceAlreadySavedException.class)
    public void updateEmailFail() throws IOException {
        Mockito.when(userRepo.findUserById(testUser2.getUserId())).thenReturn(Optional.of(testUser2));
        Mockito.when(userRepo.findUserByEmail(testUser2.getEmail())).thenReturn(Optional.of(testUser3));
        sut.update(testUser2);
    }

    @Test(expected = ResourceAlreadySavedException.class)
    public void updateUsernameFail() throws IOException{
        Mockito.when(userRepo.findUserById(testUser2.getUserId())).thenReturn(Optional.of(testUser2));
        Mockito.when(userRepo.findUserByEmail(testUser2.getEmail())).thenThrow(NoResultException.class);
        Mockito.when(userRepo.findUserByUsername(testUser2.getUsername())).thenReturn(Optional.of(testUser3));
        sut.update(testUser2);
    }

    @Test
    public void updateUserTrue() throws IOException{
        Mockito.when(userRepo.findUserById(testUser2.getUserId())).thenReturn(Optional.of(testUser2));
        Mockito.when(userRepo.findUserByEmail(testUser2.getEmail())).thenThrow(NoResultException.class);
        Mockito.when(userRepo.findUserByUsername(testUser2.getUsername())).thenThrow(NoResultException.class);
        sut.update(testUser2);
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
    @Test(expected = InvalidRequestException.class)
    public void updatePasswordNullPassword(){
        sut.updatePassword(testUser2, null);
    }

    @Test(expected = InvalidRequestException.class)
    public void updatePasswordEmptyPassword(){
        sut.updatePassword(testUser2, "");
    }

    @Test(expected = FailedTransactionException.class)
    public void updatePasswordFailedTransaction(){
        Mockito.when(userRepo.findUserById(testUser2.getUserId())).thenReturn(Optional.of(testUser3));
        sut.updatePassword(testUser2, "5");
    }

    @Test
    public void updatePasswordTrue(){
        User testUser2WithPassword = testUser2;
        testUser2WithPassword.setPassword("5");
        Mockito.when(userRepo.findUserById(testUser2.getUserId())).thenReturn(Optional.of(testUser2WithPassword));
        sut.updatePassword(testUser2, "5");
    }

    //teardown
    @After
    public void tearDown() {
        sut = null;
        mockUsers.removeAll(mockUsers);
    }
}
