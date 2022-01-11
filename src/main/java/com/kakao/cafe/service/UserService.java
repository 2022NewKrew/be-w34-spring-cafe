package com.kakao.cafe.service;

import com.kakao.cafe.vo.User;

import java.util.List;

public interface UserService {
    void addUser(User user);
    List<User> findUsers();
    User findUserById(Long id);
}
