package com.revature.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException() {
        super("No resource found with the provided search criteria!");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
