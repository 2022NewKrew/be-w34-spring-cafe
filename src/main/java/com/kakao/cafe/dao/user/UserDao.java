package com.kakao.cafe.dao.user;

import com.kakao.cafe.model.User;

import java.util.List;

public interface UserDao {
    List<User> getUsers();

    void add(User user);

    User findUserByUserId(String userId);

    int getSize();
}
