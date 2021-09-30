package org.example.movie.service;

import org.example.movie.exception.InsufficientPrivilegesException;
import org.example.movie.exception.MovieNotFoundException;
import org.example.movie.model.Movie;
import org.example.movie.port.repository.MovieRepository;
import org.example.movie.service.testdatabuilder.MovieDataTestBuilder;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

public class MovieDeleteServiceTest {
    private MovieDeleteService subject;
    private MovieRepository repository;

    @Before
    public void arrange() {
        repository = mock(MovieRepository.class);
        subject = new MovieDeleteService(repository);
    }

    @Test
    public void fail_when_movie_doesnt_exists() {
        Movie movie = new MovieDataTestBuilder().withId(1L).build();
        when(repository.find(movie.getId())).thenReturn(Optional.empty());

        assertThrows("The movies doesn't exists.", MovieNotFoundException.class, () -> subject.execute(movie.getId(), movie.getCreator()));

        verify(repository, times(0)).delete(movie.getId());
    }

    @Test
    public void fail_when_movie_user_isnt_owner() {
        Movie existing = new MovieDataTestBuilder().withId(1L).build();
        Movie movie = new MovieDataTestBuilder().withId(1L).withCreator("correo@correo.com").build();
        when(repository.find(existing.getId())).thenReturn(Optional.of(existing));

        assertThrows("User doesn't have enough privileges to modify the movie.", InsufficientPrivilegesException.class, () -> subject.execute(movie.getId(), movie.getCreator()));

        verify(repository, times(0)).delete(movie.getId());
    }

    @Test
    public void success_deletion() {
        Movie movie = new MovieDataTestBuilder().withId(1L).build();
        when(repository.find(movie.getId())).thenReturn(Optional.of(movie));

        subject.execute(movie.getId(), movie.getCreator());

        verify(repository, times(1)).delete(movie.getId());
    }
}
