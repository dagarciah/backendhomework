package com.example.movie.adapter.repository;

import org.example.movie.model.AccessType;
import org.example.movie.model.Movie;
import org.example.user.model.entity.Email;
import org.example.user.model.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class MovieRowMapper implements RowMapper<Movie> {
    @Override
    public Movie mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return Movie.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .accessType(AccessType.valueOf(resultSet.getString("access_type")))
                .duration(Duration.of(resultSet.getLong("duration"), ChronoUnit.MINUTES))
                .language(resultSet.getString("language"))
                .releaseDate(extraerLocalDate(resultSet, "release_date"))
                .resume(resultSet.getString("resume"))
                .creator(extractCreator(resultSet))
                .build();
    }

    private User extractCreator(ResultSet resultSet) throws SQLException {
        return User.builder()
                .id(resultSet.getLong("user_id"))
                .email(new Email(resultSet.getString("user_email")))
                .build();
    }

    LocalDate extraerLocalDate(ResultSet resultSet, String label) throws SQLException {
        Timestamp fecha = resultSet.getTimestamp(label);
        LocalDate resultado = null;
        if (!resultSet.wasNull()) {
            resultado = fecha.toLocalDateTime().toLocalDate();
        }
        return resultado;
    }
}
