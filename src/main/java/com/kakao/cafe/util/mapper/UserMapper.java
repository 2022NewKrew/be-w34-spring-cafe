package com.kakao.cafe.util.mapper;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.dto.UserDto;

public class UserMapper {
    public static User toUser(UserDto userDto) {
        return new User(userDto.getEmail(), userDto.getUserId(), userDto.getName(), userDto.getPassword());
    }

    public static UserDto toDto(User user) {
        return new UserDto(user.getName(), user.getPassword(), user.getId(), user.getEmail());
    }
}
