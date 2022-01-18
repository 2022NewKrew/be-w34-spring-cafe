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

    public void updateUser(User changedUser) {
        jdbcTemplatesUser.updateUser(changedUser);
    }

    public boolean validateUser(String userId, User user){
        if (userId.compareTo(user.getUserId()) != 0) {
            throw new IllegalArgumentException("사용자의 정보를 수정할 수 없습니다.");
        }
        return false;
    }

}
