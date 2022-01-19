package com.kakao.cafe.repository;

import com.kakao.cafe.entity.User;
import com.kakao.cafe.util.Page;
import com.kakao.cafe.util.Pageable;

import java.util.Optional;

public interface UserRepository {
    void save(User entity);

    void update(User entity);

    Optional<User> findByEmail(User entity);

    Page<User> findAll(Pageable pageable);
}
