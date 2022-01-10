package com.kakao.cafe.dao;

import com.kakao.cafe.model.User;

import java.util.List;

public interface CafeUserDao {
    void signIn(User newUser);
    List<User> getUserList();
    User getUserProfile(String userId);
}
