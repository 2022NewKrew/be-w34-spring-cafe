package com.kakao.cafe.repository;

import com.kakao.cafe.entity.User;
import com.kakao.cafe.util.UserMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public class JdbcUserRepository {

    private final JdbcTemplate jdbcTemplate;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public JdbcUserRepository(JdbcTemplate jdbcTemplate, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.jdbcTemplate = jdbcTemplate;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public void createUser(User user) {
        String sqlQuery = "insert into USER (USER_ID, password, EMAIL) values (?, ?, ?)";

        jdbcTemplate.update(sqlQuery, user.getUserId(), user.getPassword(), user.getEmail());

    }

    public List<User> readUsers() {
        return null;
    }

    public User readUser(String userId) {
        return null;
    }

}
