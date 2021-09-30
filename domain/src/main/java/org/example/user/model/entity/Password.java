package org.example.user.model.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static org.example.ArgumentValidator.regexValidation;
import static org.example.ArgumentValidator.requiredValidation;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Password {
    public static final String INVALID_FORMAT_MESSAGE = "Valid password must contains at least 10 characters, one lowercase letter, one uppercase letter and one of the following characters: !, @, #, ? or ].";
    public static final String REQUIRED_MESSAGE = "An password is required";

    public static final String VALID_EMAIL_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$?\\]])(?=\\S+$).{10,}$";
    private final String value;

    public static Password of (String value) {
        requiredValidation(value, REQUIRED_MESSAGE);
        regexValidation(value, VALID_EMAIL_REGEX, INVALID_FORMAT_MESSAGE);

        return new Password(value);
    }

    public static Password ofHash(String hash) {
        return new Password(hash);
    }
}
