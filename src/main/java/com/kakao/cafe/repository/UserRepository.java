package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;

import java.util.List;
import java.util.Optional;


public interface UserRepository {
    List<User> selectAll();

    Optional<User> selectByKey(Long key);

    Long insert(User user);

    void update(Long key, User user);
}