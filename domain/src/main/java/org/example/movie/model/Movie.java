package org.example.movie.model;

import lombok.Builder;
import lombok.Getter;
import org.example.user.model.entity.User;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

import static org.example.ArgumentValidator.requiredValidation;

@Getter
public class Movie {
    private final Long id;
    private final String name;
    private final String resume;
    private final Duration duration;
    private final LocalDate releaseDate;
    private final String language;
    private final AccessType accessType;
    private final User creator;

    @Builder
    public Movie(Long id, String name, String resume, Duration duration, LocalDate releaseDate, String language, AccessType accessType, User creator) {
        requiredValidation(name, "Name is a required field.");
        requiredValidation(resume, "Resume is a required field.");
        requiredValidation(duration, "Duration is a required field.");
        requiredValidation(releaseDate, "Release date is a required field.");
        requiredValidation(language, "Language is a required field.");
        requiredValidation(creator, "User creator is a required field.");

        this.id = id;
        this.name = name;
        this.resume = resume;
        this.creator = creator;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.language = language;
        this.accessType = Optional.ofNullable(accessType).orElse(AccessType.PUBLIC);
    }

    public boolean cannotEdit(User user) {
        return !Objects.equals(creator.getEmail().getValue(), user.getEmail().getValue());
    }
}
