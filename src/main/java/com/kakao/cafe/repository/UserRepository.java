package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    void save(User newUser);
    Optional<List<User>> findAll();
    User findByUserId(String userId);
    User findByEmail(String email);
}
