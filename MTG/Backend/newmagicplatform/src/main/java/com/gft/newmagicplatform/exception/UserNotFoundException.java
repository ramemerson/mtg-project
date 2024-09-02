package com.gft.newmagicplatform.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long id) {
        super("The user with id: " + id + " does not exist in our records.");
    }

    public UserNotFoundException(String username) {
        super("The user " + "'" + username + "'" + " does not exist in our records");
    }

}
