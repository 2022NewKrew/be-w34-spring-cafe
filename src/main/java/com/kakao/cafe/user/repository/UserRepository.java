package com.kakao.cafe.user.repository;

import com.kakao.cafe.user.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Long add(User user);

    List<User> getAll();

    Optional<User> getByUsername(String username);

    Optional<User> getByEmail(String email);

    Optional<User> getById(Long id);

    void update(User user);

    void remove(Long id);
}
