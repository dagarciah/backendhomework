package com.example.like.controller;

import com.example.ApplicationMock;
import com.example.movie.command.MovieCommand;
import com.example.movie.controller.testdatabuilder.MovieCommandTestDataBuilder;
import com.example.user.adapter.factory.TokenJwtFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.user.model.entity.Email;
import org.example.user.model.entity.Password;
import org.example.user.model.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;

@RunWith(SpringRunner.class)
@WebMvcTest(LikeCommandController.class)
@ContextConfiguration(classes = ApplicationMock.class)
public class LikeCommandControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TokenJwtFactory tokenFactory;

    @Test
    public void success_like() throws Exception {
        String token = getToken();

        MovieCommand command = new MovieCommandTestDataBuilder().build();
        mockMvc.perform(patch("/like/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .content(objectMapper.writeValueAsString(command)));
    }

    private String getToken() {
        return tokenFactory.create(User.builder()
                .email(new Email("user@correo.com"))
                .password(Password.of("Hello@#!?]"))
                .build());
    }
}
