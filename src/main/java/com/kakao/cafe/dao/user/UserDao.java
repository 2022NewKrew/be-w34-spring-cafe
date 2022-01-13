package com.kakao.cafe.dao.user;

import com.kakao.cafe.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    List<User> getUsers();

    void addUser(String userId, String password, String name, String email);

    Optional<User> findUserById(String userId);

    int getSize();

    void update(String userId, String name, String email);
}
