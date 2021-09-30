package org.example.user.service;

import org.example.user.exception.UserInvalidCredentialsException;
import org.example.user.exception.UserNotFoundException;
import org.example.user.model.entity.User;
import org.example.user.port.dao.UserDao;
import org.example.user.port.factory.TokenFactory;
import org.example.user.service.testdatabuilder.UserTestDataBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UserAuthenticationServiceTest {

    private UserAuthenticationService subject;
    private UserDao dao;
    private TokenFactory factory;

    @Before
    public void arrange(){
        dao = mock(UserDao.class);
        factory = mock(TokenFactory.class);
        subject = new UserAuthenticationService(dao, factory);
    }

    @Test
    public void authentication_success() {
        // Arrange
        String expectedToken = "ABC123";
        User user = new UserTestDataBuilder().build();
        when(dao.matches(user)).thenReturn(true);
        when(dao.exists(user.getEmail())).thenReturn(true);
        when(factory.create(user)).thenReturn(expectedToken);

        // Act
        String token = subject.execute(user);

        // Assert
        assertEquals(expectedToken, token);
    }

    @Test
    public void authentication_fail() {
        // Arrange
        User user = new UserTestDataBuilder().build();
        when(dao.exists(user.getEmail())).thenReturn(true);
        when(dao.matches(user)).thenReturn(false);

        // Act - Assert
        assertThrows("Invalid username or password.", UserInvalidCredentialsException.class, () -> subject.execute(user));

        // Assert
        verify(factory, times(0)).create(user);
    }

    @Test
    public void authentication_fail_user_not_found() {
        // Arrange
        User user = new UserTestDataBuilder().build();
        when(dao.exists(user.getEmail())).thenReturn(false);

        // Act - Assert
        assertThrows("User \"correo@correo.com\" doesn't exists", UserNotFoundException.class, () -> subject.execute(user));

        // Assert
        verify(factory, times(0)).create(user);
        verify(dao, times(0)).matches(user);
    }
}
