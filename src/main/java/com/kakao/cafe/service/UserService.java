package com.kakao.cafe.service;

import com.kakao.cafe.vo.User;

import java.util.List;

public interface UserService {
    void join(User user);

    List<User> getUsers();

}
