package com.kakao.cafe.service;

import com.kakao.cafe.vo.User;

import java.util.List;

public interface UserService {
    long insertUser(User user);

    List<User> getUserList();

    User getUserById(long id);

    int updateUser(User user);
}
