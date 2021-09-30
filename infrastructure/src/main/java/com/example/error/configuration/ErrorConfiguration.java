package com.example.error.configuration;

import com.example.error.ExceptionStatus;
import org.example.exception.InvalidValueException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ErrorConfiguration {

    @Bean
    ExceptionStatus invalidValueException() {
        return ExceptionStatus.with(InvalidValueException.class.getSimpleName(), 400);
    }
}
