package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;

import java.util.List;
import java.util.Optional;


public interface UserRepository {
    Long save(User user);

    List<User> findAll();

    Optional<User> findByUserId(String userId);

    Optional<User> findById(Long id);

    void updateUser(Long id, User updateUser);
}
