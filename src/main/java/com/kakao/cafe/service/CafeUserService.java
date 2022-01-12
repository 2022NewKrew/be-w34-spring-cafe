package com.kakao.cafe.service;

import com.kakao.cafe.model.User;

import java.util.List;

public interface CafeUserService {
    void signUp(User newUser);
    List<User> getUserList();
    User getUserProfile(String userId);
}
