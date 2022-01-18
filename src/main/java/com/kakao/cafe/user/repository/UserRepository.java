package com.kakao.cafe.user.repository;

import com.kakao.cafe.user.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user);

    Optional<User> findOneById(Long id);

    Optional<User> findOneByUserId(String userId);

    List<User> findAll();

    void update(User suer);

    void clear();
}
