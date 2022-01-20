package com.kakao.cafe.service;

import com.kakao.cafe.model.User;

import java.util.List;

public interface CafeUserService {
    boolean signUp(User newUser);
    boolean SignIn(User signInUser);
    List<User> getUserList();
    User getUserProfile(String userId);

    boolean adminEditProfile (String loginUser, String inputPassword);
    boolean editProfile (String loginUser, User updateUser);

    boolean deleteProfile(String userId);
}
