package com.kakao.cafe.persistence.user;

import com.kakao.cafe.domain.user.FindUserPort;
import com.kakao.cafe.domain.user.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class FindUserH2Adaptor implements FindUserPort {
    private static final String TABLE_NAME = "USER";
    private static final List<String> FIELDS = List.of("USER_ID", "USER_PASSWORD", "USER_NAME", "USER_EMAIL");
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public FindUserH2Adaptor(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        String SELECT_BY_USER_ID = "SELECT " + String.join(", ", FIELDS) + " FROM " + TABLE_NAME + " WHERE USER_ID = :userId LIMIT 1";

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("userId", userId);

        try {
            return Optional.of(
                    jdbcTemplate.queryForObject(SELECT_BY_USER_ID, parameters, (result, row) ->
                            new User(result.getString("USER_ID"),
                                    result.getString("USER_PASSWORD"),
                                    result.getString("USER_NAME"),
                                    result.getString("USER_EMAIL")))
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<User> findAll() {
        String SELECT_ALL = "SELECT " + String.join(", ", FIELDS) + " FROM " + TABLE_NAME;

        SqlParameterSource parameters = new MapSqlParameterSource();

        List<User> users = jdbcTemplate.query(SELECT_ALL, parameters, (result, row) ->
                new User(result.getString("user_id"),
                        result.getString("user_password"),
                        result.getString("user_name"),
                        result.getString("user_email"))
        );
        return users;
    }

    @Override
    public Optional<User> findByUserIdAndPassword(String userId, String password) {
        String SELECT_BY_USER_ID_AND_PASSWORD = "SELECT " + String.join(", ", FIELDS) + " FROM " + TABLE_NAME + " WHERE USER_ID = :userId AND USER_PASSWORD = :password LIMIT 1";

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("userId", userId)
                .addValue("password", password);

        try {
            return Optional.of(
                    jdbcTemplate.queryForObject(SELECT_BY_USER_ID_AND_PASSWORD, parameters, (result, row) ->
                            new User(result.getString("USER_ID"),
                                    result.getString("USER_PASSWORD"),
                                    result.getString("USER_NAME"),
                                    result.getString("USER_EMAIL")))
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
