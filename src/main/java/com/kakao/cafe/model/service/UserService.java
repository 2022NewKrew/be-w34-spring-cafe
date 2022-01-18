package com.kakao.cafe.model.service;

import com.kakao.cafe.model.dto.UserDto;

import java.util.List;

public interface UserService {
    void registerUser(UserDto userDto);

    List<UserDto> findAllUsers();

    UserDto findUserById(Long id);

    UserDto findUserByUserId(String userId);

    UserDto findUserByLoginInfo(String userId, String password, String errorMessage);

    void modifyUser(UserDto userDto);

    void withdrawUser(String userId, String password);
}
