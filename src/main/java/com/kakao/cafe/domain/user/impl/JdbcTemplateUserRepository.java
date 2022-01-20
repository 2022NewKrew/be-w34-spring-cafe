package com.kakao.cafe.domain.user.impl;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserMapper;
import com.kakao.cafe.domain.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class JdbcTemplateUserRepository implements UserRepository {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final JdbcTemplate jdbcTemplate;
    private final UserMapper userMapper;

    public JdbcTemplateUserRepository(DataSource dataSource, UserMapper userMapper) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        this.userMapper = userMapper;
    }

    @Override
    public void add(User user) {
        jdbcTemplate.update("INSERT INTO `USER` (userId, email, nickname, password) VALUES (?, ?, ?, ?)",
                user.getUserId(), user.getEmail(), user.getNickname(), user.getPassword());
    }

    @Override
    public void update(User user) {
        jdbcTemplate.update("UPDATE `USER` SET email=?, nickname=?, password=? WHERE id=?",
                user.getEmail(), user.getNickname(), user.getPassword(), user.getId());
    }

    @Override
    public Optional<User> findById(long id) {
        return jdbcTemplate.query("SELECT id, userId, email, nickname, password FROM `USER` WHERE id=? LIMIT 1", userMapper, id)
                .stream().findAny();
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        return jdbcTemplate.query("SELECT id, userId, email, nickname, password FROM `USER` WHERE userId=? LIMIT 1", userMapper, userId)
                .stream().findAny();
    }

    @Override
    public Optional<User> findByUserIdAndPassword(String userId, String password) {
        return jdbcTemplate.query("SELECT id, userId, email, nickname, password FROM `USER` WHERE userId=? AND password=? LIMIT 1", userMapper, userId, password)
                .stream().findAny();
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("SELECT id, userId, email, nickname, password FROM `USER`", userMapper);
    }
}
