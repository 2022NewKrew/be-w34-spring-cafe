package com.kakao.cafe.repository.user;

import com.kakao.cafe.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    void save(User user);

    Optional<User> findByUserId(String userId);

    List<User> findAll();

    void update(User user);
}
