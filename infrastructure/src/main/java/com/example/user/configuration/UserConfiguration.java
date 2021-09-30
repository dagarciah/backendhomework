package com.example.user.configuration;

import com.example.error.ExceptionStatus;
import com.example.user.command.handler.UserRegistrationHandler;
import com.example.user.query.UserFactory;
import com.example.user.query.handler.UserAuthenticationHandler;
import org.example.user.exception.UserAlreadyExistsException;
import org.example.user.exception.UserInvalidCredentialsException;
import org.example.user.exception.UserNotFoundException;
import org.example.user.exception.UserSessionExpiredException;
import org.example.user.port.dao.UserDao;
import org.example.user.port.factory.TokenFactory;
import org.example.user.port.repository.UserRepository;
import org.example.user.service.UserAuthenticationService;
import org.example.user.service.UserRegistrationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfiguration {
    @Bean
    UserRegistrationHandler userRegistrationHandler(UserRegistrationService userRegistrationService) {
        return new UserRegistrationHandler(userRegistrationService);
    }

    @Bean
    UserAuthenticationHandler userAuthenticationHandler(UserFactory factory, UserAuthenticationService service) {
        return new UserAuthenticationHandler(factory, service);
    }

    @Bean
    UserRegistrationService userRegistrationService(UserRepository userRepository) {
        return new UserRegistrationService(userRepository);
    }

    @Bean
    UserAuthenticationService userAuthenticationService(TokenFactory factory, UserDao dao) {
        return new UserAuthenticationService(dao, factory);
    }

    @Bean
    UserFactory userFactory() {
        return new UserFactory();
    }

    @Bean
    ExceptionStatus userAlreadyExists() {
        return ExceptionStatus.with(UserAlreadyExistsException.class.getSimpleName(), 400);
    }

    @Bean
    ExceptionStatus userInvalidCredentials() {
        return ExceptionStatus.with(UserInvalidCredentialsException.class.getSimpleName(), 401);
    }

    @Bean
    ExceptionStatus userNotFound() {
        return ExceptionStatus.with(UserNotFoundException.class.getSimpleName(), 400);
    }

    @Bean
    ExceptionStatus userSessionExpired() {
        return ExceptionStatus.with(UserSessionExpiredException.class.getSimpleName(), 401);
    }
}
