package com.example.movie.adapter.repository;

import org.example.movie.model.Movie;
import org.example.movie.port.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;

@Component
@PropertySource("classpath:sql/movie.properties")
public class MovieRepositorySql implements MovieRepository {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final NamedParameterJdbcTemplate jdbc;

    @Value("${movie.matches}")
    private String sqlMatches;

    @Value("${movie.create}")
    private String sqlCreate;

    @Value("${movie.find}")
    private String sqlFind;

    @Value("${movie.update}")
    private String sqlUpdate;

    @Value("${movie.delete}")
    private String sqlDelete;

    public MovieRepositorySql(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public boolean matches(Movie movie) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", movie.getName());
        params.addValue("releaseDate", movie.getReleaseDate().format(DATE_FORMAT));
        params.addValue("userId", movie.getCreator().getId());

        return Objects.equals(Boolean.TRUE, jdbc.queryForObject(sqlMatches, params, Boolean.class));
    }

    @Override
    public Long create(Movie movie) {
        MapSqlParameterSource params = getMapSqlParameterSource(movie);
        params.addValue("userId", movie.getCreator().getId());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(sqlCreate, params, keyHolder, new String[]{"id"});
        return Optional.ofNullable(keyHolder.getKey()).map(Number::longValue)
                .orElseThrow(() -> new IllegalStateException("User id couldn't be generated"));
    }

    @Override
    public Optional<Movie> find(Long id) {
        try {
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("id", id);

            return Optional.ofNullable(jdbc.queryForObject(sqlFind, params, new MovieRowMapper()));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void update(Movie movie) {
        MapSqlParameterSource params = getMapSqlParameterSource(movie);
        params.addValue("id", movie.getId());

        jdbc.update(sqlUpdate, params);
    }

    @Override
    public boolean delete(Long id) {
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        return jdbc.update(sqlDelete, params) > 0;
    }

    private MapSqlParameterSource getMapSqlParameterSource(Movie movie) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", movie.getName());
        params.addValue("resume", movie.getResume());
        params.addValue("duration", movie.getDuration().toMinutes());
        params.addValue("releaseDate", movie.getReleaseDate().format(DATE_FORMAT));
        params.addValue("language", movie.getLanguage());
        params.addValue("accessType", movie.getAccessType().name());
        return params;
    }
}
