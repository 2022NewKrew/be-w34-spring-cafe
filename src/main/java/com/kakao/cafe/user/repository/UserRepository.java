package com.kakao.cafe.user.repository;

import com.kakao.cafe.user.entity.User;

import java.util.List;

public interface UserRepository {
    User save(User user);

    User findById(Long id);

    User findByUserId(String userId);

    List<User> findAll();
}
