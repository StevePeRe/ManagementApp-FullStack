package com.stevecoder.managementApp.user.domain.exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String field) {
        super("User already exists with " + field);
    }
}
