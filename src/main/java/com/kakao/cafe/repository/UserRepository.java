package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;

import java.sql.SQLException;
import java.util.List;

public interface UserRepository {
    void save(User user) throws SQLException;
    List<User> findAll();
    User findByUserId(String s);
    User findByName(String s);
    void update(User user);
}
