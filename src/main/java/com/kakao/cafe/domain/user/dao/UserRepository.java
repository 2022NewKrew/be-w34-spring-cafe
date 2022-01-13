package com.kakao.cafe.domain.user.dao;

import com.kakao.cafe.domain.user.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    void save(User user);

    void update(User user);

    Optional<User> findById(Long id);

    Optional<User> findByUserId(String userId);

    List<User> findAll();
}
