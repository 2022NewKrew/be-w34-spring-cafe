package com.kakao.cafe.repository;

import com.kakao.cafe.entity.User;
import com.kakao.cafe.util.UserMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

public class JdbcUserRepository implements Repository<User, Long> {

    private final JdbcTemplate jdbcTemplate;
    private final UserMapper userMapper;

    public JdbcUserRepository(JdbcTemplate jdbcTemplate, UserMapper userMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.userMapper = userMapper;
    }

    @Override
    public void create(User user) {
        String sqlQuery = "insert into USER (USER_ID, password, EMAIL) values (?, ?, ?)";

        jdbcTemplate.update(sqlQuery, user.getUserId(), user.getPassword(), user.getEmail());

    }

    @Override
    public List<User> ReadAll() {
        return null;
    }

    @Override
    public Optional<User> ReadById(Long id) {
        return Optional.empty();
    }
}
