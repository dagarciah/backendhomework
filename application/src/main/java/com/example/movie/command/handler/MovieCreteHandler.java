package com.example.movie.command.handler;

import com.example.command.CommandHandler;
import com.example.command.CommandResponse;
import com.example.movie.command.MovieCommand;
import com.example.movie.command.factory.MovieFactory;
import org.example.movie.model.Movie;
import org.example.movie.service.MovieCreateService;

public class MovieCreteHandler implements CommandHandler<MovieCommand, Long> {
    private final MovieFactory factory;
    private final MovieCreateService service;

    public MovieCreteHandler(MovieFactory factory, MovieCreateService service) {
        this.factory = factory;
        this.service = service;
    }

    public CommandResponse<Long> execute(MovieCommand command) {
        Movie movie = factory.create(command);
        return CommandResponse.of(service.execute(movie));
    }
}
