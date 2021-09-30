package org.example.user.exception;

import org.example.user.model.entity.Email;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Email email) {
        super("User \"" + email.getValue() + "\" doesn't exists");
    }
}
