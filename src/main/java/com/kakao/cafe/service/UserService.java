package com.kakao.cafe.service;

import com.kakao.cafe.dto.UserCreateDto;
import com.kakao.cafe.dto.UserDto;

import java.util.List;

public interface UserService {
    void save(UserCreateDto userCreateDto);

    List<UserDto> getUserList();
    UserDto getUser(String userId);
}
