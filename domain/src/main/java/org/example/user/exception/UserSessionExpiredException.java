package org.example.user.exception;

public class UserSessionExpiredException extends RuntimeException {
    public UserSessionExpiredException() {
        super("The session is expired");
    }
}
