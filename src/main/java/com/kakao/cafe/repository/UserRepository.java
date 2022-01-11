package com.kakao.cafe.repository;

import com.kakao.cafe.domain.user.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User signUp(User user);
    List<User> findAll();
    Optional<User> findByUserId(String userId);
}
