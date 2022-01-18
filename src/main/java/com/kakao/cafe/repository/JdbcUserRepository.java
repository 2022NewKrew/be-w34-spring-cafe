package com.kakao.cafe.repository;

import com.kakao.cafe.entity.User;
import com.kakao.cafe.util.UserMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

public class JdbcUserRepository implements Repository<User, Integer>{

    private final JdbcTemplate jdbcTemplate;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public JdbcUserRepository(JdbcTemplate jdbcTemplate, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.jdbcTemplate = jdbcTemplate;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void create(User user) {
        String sqlQuery = "insert into MEMBER (userId, PASSWORD, EMAIL) values (?, ?, ?)";
        jdbcTemplate.update(sqlQuery, user.getUserId(), passwordEncoder.encode(user.getPassword()), user.getEmail());
    }

    @Override
    public List<User> readAll() {
        return jdbcTemplate.query("SELECT * FROM MEMBER", userMapper);
    }

    @Override
    public Optional<User> readById(Integer id) {
        String sqlQuery = "SELECT * FROM MEMBER WHERE id = ?";
        User user = jdbcTemplate.queryForObject(sqlQuery, userMapper, id);
        return Optional.ofNullable(user);
    }

}
