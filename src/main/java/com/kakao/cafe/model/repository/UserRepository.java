package com.kakao.cafe.model.repository;

import com.kakao.cafe.model.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    boolean saveUser(User user);

    List<User> findAllUsers();
    Optional<User> findUserById(Long id);
    Optional<User> findUserByUserId(String userId);
    Optional<User> findUserByLoginInfo(String userId, String password);

    boolean modifyUser(User user);

    boolean deleteUser(String userId, String password);
}
