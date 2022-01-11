package com.kakao.cafe.service.user;

import com.kakao.cafe.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto register(UserDto userDto);

    void login(UserDto userDto);

    List<UserDto> allUsers();
}
