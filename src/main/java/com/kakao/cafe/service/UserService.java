package com.kakao.cafe.service;

import com.kakao.cafe.dto.UserRegistrationDto;
import com.kakao.cafe.entity.User;

import java.util.List;

public interface UserService {
    void join(UserRegistrationDto userDto);

    List<User> getUsers();

    User findById(Integer id);

}
