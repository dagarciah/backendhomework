package com.example.movie.command.factory;

import com.example.movie.command.MovieCommand;
import org.example.movie.model.Movie;
import org.example.user.port.repository.UserRepository;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class MovieFactory {
    private final UserRepository repository;

    public MovieFactory(UserRepository repository) {
        this.repository = repository;
    }

    public Movie create(MovieCommand command) {
        return Movie.builder()
                .id(command.getId())
                .name(command.getName())
                .accessType(command.getAccessType())
                .duration(Duration.of(command.getDurationInMinutes(), ChronoUnit.MINUTES))
                .language(command.getLanguage())
                .releaseDate(command.getReleaseDate())
                .resume(command.getResume())
                .creator(repository.findBy(command.getUser().getEmail()))
                .build();
    }
}
