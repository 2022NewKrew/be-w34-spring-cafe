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
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final UserRowMapper userRowMapper;

    public FindUserH2Adaptor(NamedParameterJdbcTemplate jdbcTemplate, UserRowMapper userRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.userRowMapper = userRowMapper;
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("user_id", userId);

        try {
            return Optional.of(
                    jdbcTemplate.queryForObject(H2UserQueryBuilder.selectOneByField("user_id"), parameters, userRowMapper)
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<User> findAll() {
        SqlParameterSource parameters = new MapSqlParameterSource();

        List<User> users = jdbcTemplate.query(H2UserQueryBuilder.selectAll(), parameters, userRowMapper);
        return users;
    }

    @Override
    public Optional<User> findByUserIdAndPassword(String userId, String password) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("user_id", userId)
                .addValue("user_password", password);

        try {
            return Optional.of(jdbcTemplate.queryForObject(H2UserQueryBuilder.selectOneByMultipleField(List.of("user_id", "user_password")), parameters, userRowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
