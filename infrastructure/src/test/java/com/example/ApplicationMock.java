package com.example;

import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.ComponentScan;

@JdbcTest
@ComponentScan("com.example")
public class ApplicationMock {
}
