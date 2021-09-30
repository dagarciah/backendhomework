package org.example.user.model.entity;

import org.example.exception.InvalidValueException;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class PasswordTest {
    @Test
    public void success_when_a_valid_password() {
        // Arrange - Act
        Password password = Password.of("Hello@#!]?");

        // Assert
        assertNotNull(password);
    }

    @Test
    public void fail_when_a_lowercase_is_absent() {
        // Arrange - Act - Assert
        Assert.assertThrows(Password.INVALID_FORMAT_MESSAGE, InvalidValueException.class, () -> Password.of("HELLO@#!]?"));
    }

    @Test
    public void fail_when_an_uppercase_is_absent() {
        // Arrange - Act - Assert
        Assert.assertThrows(Password.INVALID_FORMAT_MESSAGE, InvalidValueException.class, () -> Password.of("hello@#!]?"));
    }

    @Test
    public void fail_when_a_symbol_is_absent() {
        // Arrange - Act - Assert
        Assert.assertThrows(Password.INVALID_FORMAT_MESSAGE, InvalidValueException.class, () -> Password.of("HelloWorld"));
    }

    @Test
    public void fail_when_password_has_nine_chars() {
        // Arrange - Act - Assert
        Assert.assertThrows(Password.INVALID_FORMAT_MESSAGE, InvalidValueException.class, () -> Password.of("Hello@#!]"));
    }
}
