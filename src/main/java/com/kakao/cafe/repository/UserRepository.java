package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;

import java.util.Optional;

public interface UserRepository {
    void save(User user);

    Optional<User> findUserByUserId(String userId);
}
