package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Password;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.domain.UserId;
import com.kakao.cafe.util.mapper.UserMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserJdbcRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(User user) {
        String sql = "INSERT INTO USERS(USER_ID, PASSWORD, NAME, EMAIL) VALUES(?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getUserId().getUserId(), user.getPassword().getPassword(), user.getName().getName(), user.getEmail().getEmail());
    }

    @Override
    public Optional<User> findByUserId(UserId userId) {
        try {
            String sql = "SELECT USER_ID, PASSWORD, NAME, EMAIL FROM USERS WHERE USER_ID = ?";
            User user = jdbcTemplate.queryForObject(sql, new UserMapper(), userId.getUserId());
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException exception) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findByUserIdAndPassword(UserId userId, Password password) {
        // TODO: optional 처리 스트림으로 변경
        try {
            String sql = "SELECT USER_ID, PASSWORD, NAME, EMAIL FROM USERS WHERE USER_ID = ? AND PASSWORD = ?";
            User user = jdbcTemplate.queryForObject(sql, new UserMapper(), userId.getUserId(), password.getPassword());
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException exception) {
            return Optional.empty();
        }
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT USER_ID, PASSWORD, NAME, EMAIL FROM USERS";
        return jdbcTemplate.query(sql, new UserMapper());
    }

    @Override
    public void remove(User user) {
        String sql = "DELETE FROM USERS WHERE USER_ID = ?";
        jdbcTemplate.update(sql, user.getUserId().getUserId());
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE USERS SET NAME = ?, EMAIL = ? WHERE USER_ID = ?";
        jdbcTemplate.update(sql, user.getName().getName(), user.getEmail().getEmail(), user.getUserId().getUserId());
    }
}

