package com.kakao.cafe.service.user;

import com.kakao.cafe.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto register(UserDto userDto);

    UserDto login(UserDto userDto) throws Exception;

    List<UserDto> allUsers();
}
