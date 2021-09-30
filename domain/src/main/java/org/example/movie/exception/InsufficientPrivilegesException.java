package org.example.movie.exception;

public class InsufficientPrivilegesException extends RuntimeException {
    public InsufficientPrivilegesException() {
        super("User doesn't have enough privileges to modify the movie.");
    }
}
