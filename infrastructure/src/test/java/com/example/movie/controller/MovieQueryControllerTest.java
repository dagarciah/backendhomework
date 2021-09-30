package com.example.movie.controller;

import com.example.ApplicationMock;
import com.example.user.adapter.factory.TokenJwtFactory;
import org.example.movie.model.AccessType;
import org.example.user.model.entity.Email;
import org.example.user.model.entity.Password;
import org.example.user.model.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MovieQueryController.class)
@ContextConfiguration(classes = ApplicationMock.class)
public class MovieQueryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TokenJwtFactory tokenFactory;

    @Test
    public void findPublic() throws Exception {
        String token = getToken("user@correo.com");
        mockMvc.perform(get("/movie/{id}", 1L)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    public void findPrivate() throws Exception {
        String token = getToken("user@correo.com");
        mockMvc.perform(get("/movie/{id}", 2L)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(2)));
    }

    @Test
    public void failFindPrivate() throws Exception {
        String token = getToken("jose@correo.com");
        mockMvc.perform(get("/movie/{id}", 1L)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    public void list() throws Exception {
        String token = getToken("user@correo.com");
        mockMvc.perform(get("/movie")
                .param("page", "0")
                .param("size", "2")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.items.length()", is(2)));
    }

    @Test
    public void listPublic() throws Exception {
        String token = getToken("user@correo.com");
        mockMvc.perform(get("/movie")
                .param("page", "0")
                .param("size", "2")
                .param("accessType", AccessType.PUBLIC.name())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.items.length()", is(2)));
    }

    @Test
    public void listPrivate() throws Exception {
        String token = getToken("user@correo.com");
        mockMvc.perform(get("/movie")
                .param("page", "0")
                .param("size", "2")
                .param("accessType", AccessType.PRIVATE.name())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.items.length()", is(1)));
    }

    private String getToken(String email) {
        return tokenFactory.create(User.builder()
                .email(new Email(email))
                .password(Password.of("Hello@#!?]"))
                .build());
    }
}
