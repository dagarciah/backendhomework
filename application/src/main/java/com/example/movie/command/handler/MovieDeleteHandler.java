package com.example.movie.command.handler;

import com.example.command.CommandHandler;
import com.example.command.CommandResponse;
import com.example.movie.command.MovieDeleteCommand;
import org.example.movie.service.MovieDeleteService;

public class MovieDeleteHandler implements CommandHandler<MovieDeleteCommand, Boolean> {

    private final MovieDeleteService service;

    public MovieDeleteHandler(MovieDeleteService service) {
        this.service = service;
    }

    public CommandResponse<Boolean> execute(MovieDeleteCommand command) {
        return CommandResponse.of(service.execute(command.getId(), command.getUser()));
    }
}
