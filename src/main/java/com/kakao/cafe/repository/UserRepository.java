package com.kakao.cafe.repository;

import com.kakao.cafe.entiry.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User save(User user);

    Optional<User> findById(Long id);

    Optional<User> findByUserId(String userId);

    List<User> findAll();

    Optional<User> findByName(String name);

    User update(User user);
}
