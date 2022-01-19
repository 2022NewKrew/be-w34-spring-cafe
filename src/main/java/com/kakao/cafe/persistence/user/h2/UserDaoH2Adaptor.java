package com.kakao.cafe.persistence.user.h2;

import com.kakao.cafe.domain.user.FindUserPort;
import com.kakao.cafe.domain.user.SignUpUserPort;
import com.kakao.cafe.domain.user.UpdateUserPort;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.persistence.H2QueryBuilder;
import com.kakao.cafe.persistence.user.UserRowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserDaoH2Adaptor implements FindUserPort, SignUpUserPort, UpdateUserPort {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final UserRowMapper userRowMapper;
    private final H2QueryBuilder h2QueryBuilder;

    public UserDaoH2Adaptor(NamedParameterJdbcTemplate jdbcTemplate, UserRowMapper userRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.userRowMapper = userRowMapper;
        this.h2QueryBuilder = new H2QueryBuilder("user", List.of("user_id", "user_password", "user_name", "user_email"));
    }

    @Override
    public List<User> findAll() {
        SqlParameterSource parameters = new MapSqlParameterSource();

        List<User> users = jdbcTemplate.query(h2QueryBuilder.selectAll(), parameters, userRowMapper);
        return users;
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("user_id", userId);

        try {
            return Optional.of(
                    jdbcTemplate.queryForObject(h2QueryBuilder.selectOneByField("user_id"), parameters, userRowMapper)
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findByUserIdAndPassword(String userId, String password) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("user_id", userId)
                .addValue("user_password", password);

        try {
            return Optional.of(jdbcTemplate.queryForObject(h2QueryBuilder.selectOneByMultipleField(List.of("user_id", "user_password")), parameters, userRowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void save(User user) {
        Map<String, Object> parameters = Map.of("user_id", user.getUserId(),
                "user_password", user.getPassword(),
                "user_name", user.getName(),
                "user_email", user.getEmail());

        jdbcTemplate.update(h2QueryBuilder.insertOne(List.of("user_id", "user_password", "user_name", "user_email")), parameters);
    }

    @Override
    public void update(User user) {
        Map<String, Object> parameters = Map.of("user_id", user.getUserId(),
                "user_password", user.getPassword(),
                "user_name", user.getName(),
                "user_email", user.getEmail());

        jdbcTemplate.update(h2QueryBuilder.updateOne(
                List.of("user_password", "user_name", "user_email"), List.of("user_id")), parameters);
    }
}
