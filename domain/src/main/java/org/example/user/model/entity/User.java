package org.example.user.model.entity;

import lombok.Builder;
import lombok.Getter;

import static org.example.ArgumentValidator.requiredValidation;

@Getter
public class User {
    private final Long id;
    private final Email email;
    private final Password password;

    @Builder(toBuilder = true)
    public User(Long id, Email email, Password password) {
        requiredValidation(email, "Email is a required field.");

        this.id = id;
        this.email = email;
        this.password = password;
    }
}
