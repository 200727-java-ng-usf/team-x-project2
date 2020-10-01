package com.revature.exceptions;

public class ResourceAlreadySavedException extends RuntimeException {
    public ResourceAlreadySavedException(String message) {
        super(message);
    }
}
