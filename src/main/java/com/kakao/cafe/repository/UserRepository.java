package com.kakao.cafe.repository;

import com.kakao.cafe.domain.user.User;

import java.util.List;

public interface UserRepository {
    void create(User user);
    List<User> findAll();
    User findByUserId(String userId);
    User findByIDPW(String userId, String password);
}
