package com.kakao.cafe.interfaces.user.dto;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.interfaces.user.dto.request.UserDto;

public class UserMapper {
    private UserMapper() {}

    public static User convertUserDtoToEntity(UserDto userDto) {
        return new User(userDto.getUserId(),
                userDto.getPassword(),
                userDto.getName(),
                userDto.getEmail());
    }
}
