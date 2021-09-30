package com.example.movie.adapter.dao;

import com.example.movie.adapter.repository.MovieRowMapper;
import org.example.movie.model.AccessType;
import org.example.movie.model.Movie;
import org.example.movie.model.MoviePage;
import org.example.movie.port.dao.MovieDao;
import org.example.user.model.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
@PropertySource("classpath:sql/movie.properties")
public class MovieDaoSql implements MovieDao {
    private final NamedParameterJdbcTemplate jdbc;

    @Value("${movie.listQueryItems}")
    private String sqlListQueryItems;

    @Value("${movie.findRestricted}")
    private String sqlFindRestricted;

    @Value("${movie.listQueryCount}")
    private String sqlListQueryCount;

    public MovieDaoSql(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public MoviePage list(AccessType[] accessTypes, User user, int page, int size) {
        if (Objects.isNull(accessTypes) || accessTypes.length == 0) {
            accessTypes = AccessType.values();
        }

        List<AccessType> accessTypeList = Arrays.asList(accessTypes);
        String queryCount = decoreQueryWithFilter(sqlListQueryCount, accessTypeList);
        String queryItems = decoreQueryWithFilter(sqlListQueryItems, accessTypeList);

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("accessTypes", accessTypes);
        params.addValue("offset", page * size);
        params.addValue("limit", size);

        if (accessTypeList.contains(AccessType.PRIVATE)) {
            params.addValue("userId", user.getId());
        }

        Long total = jdbc.queryForObject(queryCount, params, Long.class);
        assert Objects.nonNull(total);
        List<Movie> movies = jdbc.query(queryItems, params, new MovieRowMapper());

        return MoviePage.builder()
                .items(movies)
                .pageNumber(page)
                .pageSize(size)
                .total(total)
                .build();

    }

    @Override
    public Movie find(Long id, User user) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("userId", user.getId());
        params.addValue("id", id);

        return jdbc.queryForObject(sqlFindRestricted, params, new MovieRowMapper());
    }

    private String decoreQueryWithFilter(String query, List<AccessType> accessTypeList) {
        boolean isLookingForPublic = accessTypeList.contains(AccessType.PUBLIC);
        if (isLookingForPublic) {
            query += " where (access_type = 'PUBLIC')";
        }

        if (accessTypeList.contains(AccessType.PRIVATE)) {
            query += isLookingForPublic ? " or " : " where ";
            query += " (access_type = 'PRIVATE' and user_id = :userId) ";
        }

        query += " limit :limit offset :offset";
        return query;
    }
}
