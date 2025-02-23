package com.gft.newmagicplatform.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long id) {
        super("The user with id: " + id + " does not exist in our records."); 
    }
    
}
