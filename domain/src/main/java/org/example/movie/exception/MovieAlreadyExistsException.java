package org.example.movie.exception;

import org.example.movie.model.Movie;

public class MovieAlreadyExistsException extends RuntimeException {
    public MovieAlreadyExistsException(Movie movie) {
        super("The movie \"" + movie.getName() + "\" already exists.");
    }
}
