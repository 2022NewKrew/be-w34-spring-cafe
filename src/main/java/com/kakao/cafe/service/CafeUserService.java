package com.kakao.cafe.service;

import com.kakao.cafe.model.User;

import java.util.List;

public interface CafeUserService {
    boolean signUp(User newUser);
    boolean SignIn(User signInUser);
    List<User> getUserList();
    User getUserProfile(String userId);

    boolean adminEditProfile (User user, String inputPassword);
    boolean editProfile (User user, User updateUser);

    boolean deleteProfile(String userId);
}
