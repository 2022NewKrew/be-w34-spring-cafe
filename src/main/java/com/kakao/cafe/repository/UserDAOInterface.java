package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;

import java.util.List;

public interface UserDAOInterface {
    void insert(User user);

    void editUser(String userId, String name, String email);

    public List<User> findAll();

    public User findById(String userId);
}
