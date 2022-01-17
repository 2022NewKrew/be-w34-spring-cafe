package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

public interface UserRepository {
    void save(User user) throws SQLException;
    List<User> findAll();
    User findByUserId(String s) throws NoSuchElementException;
    User findByName(String s) throws NoSuchElementException;
    void update(User user);
}
