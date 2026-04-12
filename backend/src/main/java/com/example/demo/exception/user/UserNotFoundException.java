package com.example.demo.exception.user;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long userId) {
        super("user not found.id = " + userId);
    }
}
