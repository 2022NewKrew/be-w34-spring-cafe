package com.kakao.cafe.user.repository;

import com.kakao.cafe.user.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User save(User user);
    Optional<User> findByUserId(String userId);
    List<User> findAll();
}
