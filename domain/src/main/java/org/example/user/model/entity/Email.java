package org.example.user.model.entity;

import lombok.Getter;

import static org.example.ArgumentValidator.regexCaseInsensitiveValidation;
import static org.example.ArgumentValidator.requiredValidation;

@Getter
public class Email {
    public static final String INVALID_FORMAT_MESSAGE = "Email address contains an invalid format.";
    public static final String REQUIRED_MESSAGE = "An email address is required";

    public static final String VALID_EMAIL_REGEX = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
    private final String value;

    public Email(String value) {
        requiredValidation(value, REQUIRED_MESSAGE);
        regexCaseInsensitiveValidation(value, VALID_EMAIL_REGEX, INVALID_FORMAT_MESSAGE);
        this.value = value;
    }
}
