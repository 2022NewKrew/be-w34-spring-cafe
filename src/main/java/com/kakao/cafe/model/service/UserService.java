package com.kakao.cafe.model.service;

import com.kakao.cafe.model.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    boolean registerUser(User user);
    List<User> findAllUsers();
    User findUserByID(String id);
    User findUserByLoginInfo(String id, String password);
    boolean modifyUser(User user);
    boolean withdrawUser(String id, String password);
}
