package com.kakao.cafe.repository;

import com.kakao.cafe.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    void saveUser(User user);

    Optional<User> findUserById(Long userId);

    List<User> findAll();

    void updateUser(Long userId, String name, String email, String password);
}
