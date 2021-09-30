package org.example.user.service;

import org.example.user.exception.UserAlreadyExistsException;
import org.example.user.model.entity.User;
import org.example.user.port.repository.UserRepository;
import org.example.user.service.testdatabuilder.UserTestDataBuilder;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UserRegistrationServiceTest {

    @Test
    public void success_when_email_doesnt_exists() {
        // Arrange
        User user = new UserTestDataBuilder().build();
        UserRepository repository = mock(UserRepository.class);
        UserRegistrationService subject = new UserRegistrationService(repository);

        when(repository.exists(user.getEmail())).thenReturn(false);

        // Act
        Long id = subject.execute(user);

        // Assert
        assertNotNull(id);
    }

    @Test
    public void fail_when_email_already_exists() {
        // Arrange
        User user = new UserTestDataBuilder().build();
        UserRepository repository = mock(UserRepository.class);
        UserRegistrationService subject = new UserRegistrationService(repository);

        when(repository.exists(user.getEmail())).thenReturn(true);

        // Act - Assert
        assertThrows("User \" " + user.getEmail().getValue() + " \" already exists.", UserAlreadyExistsException.class, () -> subject.execute(user));
    }
}
