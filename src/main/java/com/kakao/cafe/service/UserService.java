package com.kakao.cafe.service;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.repository.JdbcTemplatesUser;
import org.springframework.dao.DuplicateKeyException;

import java.util.List;

public class UserService {

    private JdbcTemplatesUser jdbcTemplatesUser;

    public UserService(JdbcTemplatesUser jdbcTemplatesUser) {
        this.jdbcTemplatesUser = jdbcTemplatesUser;
    }

    public void save(User user) {
        try {
            jdbcTemplatesUser.save(user);
        } catch (DuplicateKeyException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public User findOneById(String userId) {
        return jdbcTemplatesUser.findOneById(userId);
    }

    public List<User> findAll() {
        return jdbcTemplatesUser.findAll();
    }

    public List<User> logIn(String userId, String password) {
        return jdbcTemplatesUser.findOneByUserIdPassword(userId, password);
    }
}
