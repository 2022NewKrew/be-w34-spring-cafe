package com.kakao.cafe.persistence.user.h2;

import com.kakao.cafe.domain.user.FindUserPort;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.persistence.user.UserRowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.List;
import java.util.Optional;

public class FindUserH2Adaptor implements FindUserPort {
    private static final String TABLE_NAME = "USER";
    private static final List<String> FIELDS = List.of("user_id", "user_password", "user_name", "user_email");
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final UserRowMapper userRowMapper;

    public FindUserH2Adaptor(NamedParameterJdbcTemplate jdbcTemplate, UserRowMapper userRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.userRowMapper = userRowMapper;
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        String SELECT_BY_USER_ID = "SELECT " + String.join(", ", FIELDS) + " FROM " + TABLE_NAME + " WHERE USER_ID = :userId LIMIT 1";

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("userId", userId);

        try {
            return Optional.of(
                    jdbcTemplate.queryForObject(SELECT_BY_USER_ID, parameters, userRowMapper)
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<User> findAll() {
        String SELECT_ALL = "SELECT " + String.join(", ", FIELDS) + " FROM " + TABLE_NAME;

        SqlParameterSource parameters = new MapSqlParameterSource();

        List<User> users = jdbcTemplate.query(SELECT_ALL, parameters, userRowMapper);
        return users;
    }

    @Override
    public Optional<User> findByUserIdAndPassword(String userId, String password) {
        String SELECT_BY_USER_ID_AND_PASSWORD = "SELECT " + String.join(", ", FIELDS) + " FROM " + TABLE_NAME + " WHERE USER_ID = :userId AND USER_PASSWORD = :password LIMIT 1";

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("userId", userId)
                .addValue("password", password);

        try {
            return Optional.of(jdbcTemplate.queryForObject(SELECT_BY_USER_ID_AND_PASSWORD, parameters, userRowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
