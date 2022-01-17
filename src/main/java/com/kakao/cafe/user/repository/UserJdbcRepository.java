package com.kakao.cafe.user.repository;

import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.dto.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserJdbcRepository implements UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(User user) {
        String sql = "INSERT INTO USERS(USER_ID, PASSWORD, NAME, EMAIL) VALUES(?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        try {
            String sql = "SELECT USER_ID, PASSWORD, NAME, EMAIL FROM USERS WHERE USER_ID = ?";
            User user = jdbcTemplate.queryForObject(sql, new UserMapper(), userId);
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
        jdbcTemplate.update(sql, user.getUserId());
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE USERS SET NAME = ?, EMAIL = ? WHERE USER_ID = ?";
        jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getUserId());
    }
}

