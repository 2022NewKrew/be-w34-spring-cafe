package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;

import java.util.List;

public interface UserDAOInterface {
    void save(User user);

    public List<User> findAll();

    public User findById(String userId);
}
