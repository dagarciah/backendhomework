package org.example.like.model;

import lombok.Getter;
import org.example.user.model.entity.User;

import static org.example.ArgumentValidator.requiredValidation;

@Getter
public class Like {
    private final Long movieId;
    private final User user;

    private Like(Long movieId, User user) {
        requiredValidation(movieId, "The movie id is a required field");
        requiredValidation(user.getEmail(), "The user is a required field");
        this.user = user;
        this.movieId = movieId;
    }

    public static Like from(Long movieId, User user) {
        return new Like(movieId, user);
    }
}
