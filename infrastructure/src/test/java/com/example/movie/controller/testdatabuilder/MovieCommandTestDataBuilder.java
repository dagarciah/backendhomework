package com.example.movie.controller.testdatabuilder;

import com.example.movie.command.MovieCommand;
import org.example.movie.model.AccessType;

import java.time.LocalDate;

public class MovieCommandTestDataBuilder {

    private final LocalDate releaseDate;
    private final String resume;
    private final String language;
    private final int duration;
    private final AccessType accessType;
    private Long id;
    private final String name;

    public MovieCommandTestDataBuilder() {
        id = 1L;
        name = "The Comeback Trail";
        releaseDate = LocalDate.of(2020, 10, 9);
        resume = "The Comeback Trail is a 2020 American crime comedy film directed by George Gallo and written by Gallo and Josh Posner, based on the 1982 film of the same name by Harry Hurwitz.";
        language = "English";
        duration = 104;
        accessType = AccessType.PUBLIC;
    }

    public MovieCommand build() {
        return MovieCommand.builder()
                .id(id)
                .name(name)
                .releaseDate(releaseDate)
                .resume(resume)
                .language(language)
                .durationInMinutes(duration)
                .accessType(accessType)
                .build();
    }

    public MovieCommandTestDataBuilder withId(Long id) {
        this.id = id;
        return this;
    }
}
