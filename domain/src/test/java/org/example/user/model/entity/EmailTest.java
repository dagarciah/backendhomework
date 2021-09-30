package org.example.user.model.entity;

import org.example.exception.InvalidValueException;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class EmailTest {

    @Test
    public void success_when_a_valid_address() {
        // Arrange - Act
        Email email = new Email("correo@correo.com");

        // Assert
        assertNotNull(email);
    }

    @Test
    public void success_when_a_valid_uppercase_address() {
        // Arrange - Act
        Email email = new Email("CORREO@correo.com");

        // Assert
        assertNotNull(email);
    }

    @Test
    public void fail_without_at_sign() {
        // Arrange - Act - Assert
        Assert.assertThrows(Email.INVALID_FORMAT_MESSAGE, InvalidValueException.class, () -> new Email("correocorreo.com"));
    }

    @Test
    public void fail_without_domain() {
        // Arrange - Act - Assert
        Assert.assertThrows(Email.INVALID_FORMAT_MESSAGE, InvalidValueException.class, () -> new Email("correo@correo"));
    }

}
