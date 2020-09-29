package com.revature.dtos;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Location;
import com.revature.models.UserRole;

import java.util.Objects;

public class Principal {

private int userId;

private String username;

private UserRole userRole;

private Location home;

    public Principal() {
    }

    public Principal(int userId, String username, UserRole userRole, Location home) {
        this.userId = userId;
        this.username = username;
        this.userRole = userRole;
        this.home = home;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public Location getHome() {
        return home;
    }

    public void setHome(Location home) {
        home = home;
    }

    public String stringify() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Principal principal = (Principal) o;
        return userId == principal.userId &&
                Objects.equals(username, principal.username) &&
                userRole == principal.userRole &&
                Objects.equals(home, principal.home);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, userRole, home);
    }

    @Override
    public String toString() {
        return "Principal{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", userRole=" + userRole +
                ", Home=" + home +
                '}';
    }
}
