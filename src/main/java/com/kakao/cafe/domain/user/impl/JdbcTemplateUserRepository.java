package com.kakao.cafe.domain.user.impl;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserMapper;
import com.kakao.cafe.domain.user.UserRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class JdbcTemplateUserRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;
    private final UserMapper userMapper;

    public JdbcTemplateUserRepository(DataSource dataSource, UserMapper userMapper) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        this.userMapper = userMapper;
    }

    @Override
    public void add(User user) {
        jdbcTemplate.update("INSERT INTO `user` (email, userId, nickname, password) VALUES (?, ?, ?, ?)",
                user.getUserId(), user.getEmail(), user.getNickname(), user.getPassword());
    }

    @Override
    public Optional<User> findById(long id) {
        return jdbcTemplate.query("SELECT id, userId, email, nickname, password FROM `user` WHERE id=?", userMapper, id)
                .stream().findAny();
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("SELECT id, userId, email, nickname, password FROM `user`", userMapper);
    }
}
