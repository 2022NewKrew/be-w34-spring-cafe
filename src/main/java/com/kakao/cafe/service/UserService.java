package com.kakao.cafe.service;

import com.kakao.cafe.vo.User;

import java.util.List;

public interface UserService{
    void insertUser(User user);

    List<User> getUserList();

    User getUserByUserId(String userId);
}
