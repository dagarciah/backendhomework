package com.example.user.adapter.repository;

import org.example.user.exception.UserNotFoundException;
import org.example.user.model.entity.Email;
import org.example.user.model.entity.User;
import org.example.user.port.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;

@Repository
@PropertySource("classpath:sql/user.properties")
public class UserRepositorySql implements UserRepository {
    private final NamedParameterJdbcTemplate jdbc;

    @Value("${user.exists}")
    private String sqlExists;

    @Value("${user.create}")
    private String sqlCreate;

    @Value("${user.findByEmail}")
    private String sqlFindByEmail;

    public UserRepositorySql(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public boolean exists(Email email) {
        MapSqlParameterSource params = new MapSqlParameterSource("email", email.getValue());
        return Objects.equals(Boolean.TRUE, jdbc.queryForObject(sqlExists, params, Boolean.class));
    }

    @Override
    public Long create(User user) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("email", user.getEmail().getValue());
        params.addValue("password", user.getPassword().getValue());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(sqlCreate, params, keyHolder, new String[]{"id"});
        return Optional.ofNullable(keyHolder.getKey()).map(Number::longValue)
                .orElseThrow(() -> new IllegalStateException("User id couldn't be generated"));
    }

    @Override
    public User findBy(Email email) {
        try {
            MapSqlParameterSource params = new MapSqlParameterSource("email", email.getValue());
            return jdbc.queryForObject(sqlFindByEmail, params, new RowMapper<User>() {
                @Override
                public User mapRow(ResultSet resultSet, int i) throws SQLException {
                    return User.builder()
                            .id(resultSet.getLong("id"))
                            .email(new Email(resultSet.getString("email")))
                            .build();
                }
            });
        } catch (EmptyResultDataAccessException e) {
            throw new UserNotFoundException(email);
        }
    }
}
