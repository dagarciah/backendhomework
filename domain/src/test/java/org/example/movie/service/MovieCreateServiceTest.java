package org.example.movie.service;

import org.example.movie.exception.MovieAlreadyExistsException;
import org.example.movie.model.Movie;
import org.example.movie.port.repository.MovieRepository;
import org.example.movie.service.testdatabuilder.MovieDataTestBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MovieCreateServiceTest {

    private MovieCreateService subject;
    private MovieRepository repository;

    @Before
    public void arrange() {
        repository = mock(MovieRepository.class);
        subject = new MovieCreateService(repository);
    }

    @Test
    public void create_movie_successful() {
        // Arrange
        Long expectedId = 1L;
        Movie movie = new MovieDataTestBuilder().build();
        when(repository.matches(movie)).thenReturn(false);
        when(repository.create(movie)).thenReturn(expectedId);

        // Act
        Long id = subject.execute(movie);

        // Assert
        assertEquals(expectedId, id);
    }

    @Test
    public void create_movie_failed_when_already_exists() {
        // Arrange
        Movie movie = new MovieDataTestBuilder().build();
        when(repository.matches(movie)).thenReturn(true);

        // Act - Assert
        assertThrows("The movie \"" + movie.getName() + "\" already exists.", MovieAlreadyExistsException.class, () -> subject.execute(movie));
    }


}
