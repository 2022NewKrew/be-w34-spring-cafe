package com.kakao.cafe.controller.users.mapper;

import com.kakao.cafe.controller.users.dto.UserItemDto;
import com.kakao.cafe.controller.users.dto.UserProfileDto;
import com.kakao.cafe.domain.User;

public class UserDtoMapper {
    public static UserProfileDto toUserProfileDto(User user) {
        return new UserProfileDto(user.getUserName(), user.getEmail());
    }

    public static UserItemDto toUserItemDto(User user) {
        return new UserItemDto(user.getId(), user.getUserId(), user.getUserName(), user.getEmail());
    }
}
