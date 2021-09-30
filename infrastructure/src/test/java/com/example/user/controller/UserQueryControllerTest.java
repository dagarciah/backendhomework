package com.example.user.controller;

import com.example.ApplicationMock;
import com.example.user.command.UserRegistrationCommand;
import com.example.user.controller.testdatabuilder.UserRegistrationCommandTestDataBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserQueryController.class)
@ContextConfiguration(classes = ApplicationMock.class)
public class UserQueryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void success_authentication() throws Exception {
        UserRegistrationCommand command = new UserRegistrationCommandTestDataBuilder().withEmail("user@correo.com").build();
        String credentials = Base64.getEncoder()
                .encodeToString((command.getEmail() + ":" + command.getPassword()).getBytes(StandardCharsets.UTF_8));

        mockMvc.perform(
                get("/user/authentication")
                        .header(HttpHeaders.AUTHORIZATION, "Basic " + credentials))
                .andExpect(status().isOk())
                .andExpect(header().exists(HttpHeaders.AUTHORIZATION));
    }

    @Test
    public void fail_authentication_user_not_found() throws Exception {
        UserRegistrationCommand command = new UserRegistrationCommandTestDataBuilder().withEmail("correo@correo.com").build();
        String credentials = Base64.getEncoder()
                .encodeToString((command.getEmail() + ":" + command.getPassword()).getBytes(StandardCharsets.UTF_8));

        mockMvc.perform(
                get("/user/authentication")
                        .header(HttpHeaders.AUTHORIZATION, "Basic " + credentials))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void fail_authentication_bad_credentials() throws Exception {
        UserRegistrationCommand command = new UserRegistrationCommandTestDataBuilder().withPassword("Hello@#!??")
                .withEmail("user@correo.com").build();
        String credentials = Base64.getEncoder()
                .encodeToString((command.getEmail() + ":" + command.getPassword()).getBytes(StandardCharsets.UTF_8));

        mockMvc.perform(
                get("/user/authentication")
                        .header(HttpHeaders.AUTHORIZATION, "Basic " + credentials))
                .andExpect(status().isUnauthorized());
    }

}
