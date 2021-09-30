package org.example.movie.service.testdatabuilder;

import org.example.movie.model.AccessType;
import org.example.movie.model.Movie;
import org.example.user.model.entity.Email;
import org.example.user.model.entity.User;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class MovieDataTestBuilder {

    private Long id;
    private final String name;
    private final AccessType accessType;
    private final Duration duration;
    private final String language;
    private final LocalDate releaseDate;
    private final String resume;
    private User creator;

    public MovieDataTestBuilder() {
        name = "The Poison Rose";
        accessType = AccessType.PRIVATE;
        duration = Duration.of(98, ChronoUnit.MINUTES);
        language = "English";
        releaseDate = LocalDate.of(2019, 5, 24);
        resume = "The Poison Rose (released internationally as Eye for an Eye) is a 2019 American thriller film starring John Travolta and Morgan Freeman.[1] The film was directed by George Gallo and Francesco Cinquemani.";
        creator = User.builder().email(new Email("user@correo.com")).build();
    }

    public Movie build() {
        return Movie.builder()
                .id(id)
                .name(name)
                .accessType(accessType)
                .duration(duration)
                .language(language)
                .releaseDate(releaseDate)
                .resume(resume)
                .creator(creator)
                .build();
    }

    public MovieDataTestBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public MovieDataTestBuilder withCreator(String email) {
        this.creator = User.builder().email(new Email(email)).build();
        return this;
    }
}
