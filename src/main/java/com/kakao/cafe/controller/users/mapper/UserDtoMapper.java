package com.kakao.cafe.controller.users.mapper;

import com.kakao.cafe.controller.users.dto.UpdateRequestDto;
import com.kakao.cafe.controller.users.dto.UserInfoDto;
import com.kakao.cafe.controller.users.dto.UserItemDto;
import com.kakao.cafe.controller.users.dto.UserProfileDto;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.service.user.dto.UserUpdateForm;

public class UserDtoMapper {
    public static UserProfileDto toUserProfileDto(User user) {
        return new UserProfileDto(user.getUserName(), user.getEmail());
    }

    public static UserItemDto toUserItemDto(User user) {
        return new UserItemDto(user.getId(), user.getUserId(), user.getUserName(), user.getEmail());
    }

    public static UserInfoDto toUserInfoDto(User user) {
        return new UserInfoDto(user.getId(), user.getUserId(), user.getUserName(), user.getEmail());
    }

    public static UserUpdateForm toUserUpdateForm(Long id, UpdateRequestDto updateRequest) {
        return new UserUpdateForm(id, updateRequest.getUserId(), updateRequest.getPassword(), updateRequest.getName(), updateRequest.getEmail());
    }
}
