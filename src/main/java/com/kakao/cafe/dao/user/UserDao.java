package com.kakao.cafe.dao.user;

import com.kakao.cafe.model.User;

import java.util.List;

public interface UserDao {
    List<User> getUsers();

    void add(User user);

    User get(String userId);

    int getSize();

    void update(String userId, String name, String email);
}
