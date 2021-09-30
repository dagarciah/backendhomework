package org.example.user.exception;

import org.example.user.model.entity.Email;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(Email email) {
        super("User \"" + email.getValue() + "\" already exists.");
    }
}
