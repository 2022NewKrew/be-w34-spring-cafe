package com.kakao.cafe.repository;

import com.kakao.cafe.model.User;

import java.util.List;

public interface UserRepository {
    boolean signUp(User user);
    List<User> findAllUsers();
    User findUserByUserId();
}
