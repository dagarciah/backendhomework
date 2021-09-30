package org.example.movie.exception;

public class MovieNotFoundException extends RuntimeException {
    public MovieNotFoundException() {
        super("The movies doesn't exists.");
    }
}
