package com.example.user.controller;

import com.example.ApplicationMock;
import com.example.user.command.UserRegistrationCommand;
import com.example.user.controller.testdatabuilder.UserRegistrationCommandTestDataBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserCommandController.class)
@ContextConfiguration(classes = ApplicationMock.class)
public class UserCommandControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void success_registration() throws Exception {
        UserRegistrationCommand command = new UserRegistrationCommandTestDataBuilder().build();
        mockMvc.perform(
                post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(command)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value", Is.is(3)));
    }

    @Test
    public void fail_with_wrong_email() throws Exception {
        UserRegistrationCommand command = new UserRegistrationCommandTestDataBuilder().withEmail("correocorreo.com").build();
        mockMvc.perform(
                post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(command)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.type", Is.is("InvalidValueException")))
                .andExpect(jsonPath("$.message", Is.is("Email address contains an invalid format.")));
    }

    @Test
    public void fail_with_wrong_passowrd() throws Exception {
        UserRegistrationCommand command = new UserRegistrationCommandTestDataBuilder().withPassword("1234567890").build();
        mockMvc.perform(
                post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(command)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.type", Is.is("InvalidValueException")))
                .andExpect(jsonPath("$.message", Is.is("Valid password must contains at least 10 characters, one lowercase letter, one uppercase letter and one of the following characters: !, @, #, ? or ].")));
    }

    @Test
    public void fail_email_already_exists() throws Exception {
        UserRegistrationCommand command = new UserRegistrationCommandTestDataBuilder().withEmail("user@correo.com").build();
        mockMvc.perform(
                post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(command)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.type", Is.is("UserAlreadyExistsException")))
                .andExpect(jsonPath("$.message", Is.is("User \"user@correo.com\" already exists.")));
    }
}
