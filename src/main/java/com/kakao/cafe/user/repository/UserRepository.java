package com.kakao.cafe.user.repository;

import com.kakao.cafe.user.persistence.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    void save(User user);
    void update(User user);

    Optional<User> findByUserId(String userId);
    Optional<User> findById(Long id);
    Optional<User> findByUserIdAndPassword(String userId, String password);

    List<User> findAll();

}
