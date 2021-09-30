package com.example.user.adapter.dao;

import org.example.user.model.entity.Email;
import org.example.user.model.entity.User;
import org.example.user.port.dao.UserDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@PropertySource("classpath:sql/user.properties")
public class UserDaoSql implements UserDao {
    @Value("${user.matches}")
    private String sqlMatches;

    @Value("${user.exists}")
    private String sqlExists;

    private final NamedParameterJdbcTemplate jdbc;

    public UserDaoSql(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public boolean matches(User user) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("email", user.getEmail().getValue());
        params.addValue("password", user.getPassword().getValue());

        return Objects.equals(Boolean.TRUE, jdbc.queryForObject(sqlMatches, params, Boolean.class));
    }

    @Override
    public boolean exists(Email email) {
        MapSqlParameterSource params = new MapSqlParameterSource("email", email.getValue());
        return Objects.equals(Boolean.TRUE, jdbc.queryForObject(sqlExists, params, Boolean.class));
    }
}
