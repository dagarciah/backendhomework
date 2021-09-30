package com.example.movie.controller;

import com.example.ApplicationMock;
import com.example.movie.command.MovieCommand;
import com.example.movie.controller.testdatabuilder.MovieCommandTestDataBuilder;
import com.example.user.adapter.factory.TokenJwtFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.user.model.entity.Email;
import org.example.user.model.entity.Password;
import org.example.user.model.entity.User;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MovieCommandController.class)
@ContextConfiguration(classes = ApplicationMock.class)
public class MovieCommandControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TokenJwtFactory tokenFactory;

    @Test
    public void success_creation() throws Exception {
        String token = getToken("user@correo.com");

        MovieCommand command = new MovieCommandTestDataBuilder().build();
        mockMvc.perform(post("/movie")
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .content(objectMapper.writeValueAsString(command)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value", Is.isA(Integer.class)));
    }

    @Test
    public void success_update() throws Exception {
        String token = getToken("user@correo.com");

        MovieCommand command = new MovieCommandTestDataBuilder().withId(2L).build();
        mockMvc.perform(put("/movie/{id}", command.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .content(objectMapper.writeValueAsString(command)))
                .andExpect(status().isOk());
    }

    @Test
    public void fail_update() throws Exception {
        String token = getToken("jose@correo.com");

        MovieCommand command = new MovieCommandTestDataBuilder().withId(2L).build();
        mockMvc.perform(put("/movie/{id}", command.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .content(objectMapper.writeValueAsString(command)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void success_deletion() throws Exception {
        String token = getToken("user@correo.com");

        MovieCommand command = new MovieCommandTestDataBuilder().build();
        mockMvc.perform(delete("/movie/{id}", command.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .content(objectMapper.writeValueAsString(command)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value", Is.isA(Boolean.class)));
    }

    @Test
    public void fail_deletion() throws Exception {
        String token = getToken("jose@correo.com");

        MovieCommand command = new MovieCommandTestDataBuilder().withId(3L).build();
        mockMvc.perform(delete("/movie/{id}", command.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .content(objectMapper.writeValueAsString(command)))
                .andExpect(status().isUnauthorized());
    }

    private String getToken(String email) {
        return tokenFactory.create(User.builder()
                .email(new Email(email))
                .password(Password.of("Hello@#!?]"))
                .build());
    }
}
