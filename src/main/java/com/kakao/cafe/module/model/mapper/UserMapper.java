package com.kakao.cafe.module.model.mapper;

import com.kakao.cafe.module.model.domain.User;

import static com.kakao.cafe.module.model.dto.UserDtos.*;

public class UserMapper {

    public static User toUser(Long id, String userId, String password, String name, String email) {
        return new User(id, userId, password, name, email);
    }

    public static UserDto toUserDto(User user) {
        return new UserDto(user.getId(), user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
    }
}
