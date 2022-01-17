package com.kakao.cafe.repository;

import com.kakao.cafe.domain.user.User;

import com.kakao.cafe.domain.user.UserName;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    void save(User user);

    List<User> findAll();

    Optional<User> findUserByName(UserName id);

    Optional<User> findUserById(UUID id);

    void update(User user);
}
