package org.example.user.exception;

public class UserInvalidCredentialsException extends RuntimeException {
    public UserInvalidCredentialsException() {
        super("Invalid username or password.");
    }
}
