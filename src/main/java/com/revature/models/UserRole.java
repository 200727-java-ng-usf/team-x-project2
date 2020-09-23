package com.revature.models;


import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public enum UserRole {

    USER("User"),
    ADMIN("Admin"),
    LOCKED("Locked");

    private String roleName;


    UserRole(String name){
        this.roleName = name;
    }

    @Enumerated(EnumType.STRING)
    public static UserRole getByName(String name){

        for (UserRole role: UserRole.values()) {
            if (role.roleName.equals(name)) {
                return role;
            }
        }
        return LOCKED;
    }


    @Override
    public String toString() {
        return roleName;
    }
}
