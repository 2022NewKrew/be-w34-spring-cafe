package com.kakao.cafe.app.data;

import com.kakao.cafe.domain.entity.SignUp;
import com.kakao.cafe.domain.entity.User;
import com.kakao.cafe.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JdbcUserRepository implements UserRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final UserRowMapper rowMapper = new UserRowMapper();

    @Autowired
    public JdbcUserRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User create(SignUp signUp) {
        String sql = "INSERT INTO users " +
                "(user_id, password, name, email) " +
                "VALUES (:user_id, :password, :name, :email)";
        SqlParameterSource params = new MapSqlParameterSource(signUp.toMap());
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, params, holder);
        return signUp.createUser(holder.getKey().longValue());
    }

    @Override
    public List<User> list() {
        String sql = "SELECT * FROM users";
        Map<String, ?> params = Collections.emptyMap();
        return jdbcTemplate.queryForStream(sql, params, rowMapper)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<User> getById(long id) {
        String sql = "SELECT * FROM users WHERE id = :id";
        Map<String, ?> params = Collections.singletonMap("id", id);
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, params, rowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> getByUserId(String userId) {
        String sql = "SELECT * FROM users WHERE user_id = :user_id";
        Map<String, ?> params = Collections.singletonMap("user_id", userId);
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, params, rowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public User login(String userId, String password) {
        String sql = "SELECT * FROM users WHERE user_id = :user_id AND password = :password";
        Map<String, ?> params = Map.of("user_id", userId, "password", password);
        try {
            return jdbcTemplate.queryForObject(sql, params, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
