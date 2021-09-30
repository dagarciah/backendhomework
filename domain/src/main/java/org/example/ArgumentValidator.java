package org.example;

import org.example.exception.InvalidValueException;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ArgumentValidator {
    private ArgumentValidator() {
    }

    public static void regexValidation(String value, String regex, String message) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);

        if (!matcher.find()) {
            throw new InvalidValueException(message);
        }
    }

    public static void regexCaseInsensitiveValidation(String value, String regex, String message) {
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(value);

        if (!matcher.find()) {
            throw new InvalidValueException(message);
        }
    }

    public static void requiredValidation(Object value, String message) {
        if (Objects.isNull(value)) {
            throw new InvalidValueException(message);
        }

        if (value instanceof String && ((String) value).isEmpty()) {
            throw new InvalidValueException(message);
        }
    }
}
