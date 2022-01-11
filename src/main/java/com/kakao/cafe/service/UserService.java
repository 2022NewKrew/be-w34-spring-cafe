package com.kakao.cafe.service;

import com.kakao.cafe.vo.User;
import com.kakao.cafe.vo.UserDto;

import java.util.List;

public interface UserService {
    void addUser(UserDto userdto);
    List<User> findUsers();
    User findUserById(Long id);
}
