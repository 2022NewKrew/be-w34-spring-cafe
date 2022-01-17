package com.kakao.cafe.dao;

import com.kakao.cafe.model.User;

import java.util.List;

public interface CafeUserDao {
    boolean signUp(User newUser);
    boolean SignIn(User signInUser);
    List<User> getUserList();
    User getUserProfile(String userId);

    boolean adminProfileEdit (User user, String inputPassword);
}
