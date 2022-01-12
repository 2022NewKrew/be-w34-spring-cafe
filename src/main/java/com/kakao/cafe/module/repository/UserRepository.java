package com.kakao.cafe.module.repository;

import com.kakao.cafe.module.model.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    void addUser(User user);

    List<User> findAllUsers();

    User findUserById(Long id);

    Optional<User> findUserByName(String name);

    void updateUser(User user);
}
