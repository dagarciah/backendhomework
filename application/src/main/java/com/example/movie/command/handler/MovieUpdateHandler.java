package com.example.movie.command.handler;

import com.example.command.CommandHandler;
import com.example.command.CommandResponse;
import com.example.movie.command.MovieCommand;
import com.example.movie.command.factory.MovieFactory;
import org.example.movie.model.Movie;
import org.example.movie.service.MovieUpdateService;

public class MovieUpdateHandler implements CommandHandler<MovieCommand, Void> {
    private final MovieFactory factory;
    private final MovieUpdateService service;

    public MovieUpdateHandler(MovieFactory factory, MovieUpdateService service) {
        this.factory = factory;
        this.service = service;
    }

    public CommandResponse<Void> execute(MovieCommand command) {
        Movie movie = factory.create(command);
        service.execute(movie);
        return CommandResponse.empty();
    }
}
