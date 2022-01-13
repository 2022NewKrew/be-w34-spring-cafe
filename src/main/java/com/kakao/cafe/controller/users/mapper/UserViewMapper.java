package com.kakao.cafe.controller.users.mapper;

import com.kakao.cafe.controller.users.dto.response.UserItemResponse;
import com.kakao.cafe.controller.users.dto.response.UserProfileResponse;
import com.kakao.cafe.controller.users.dto.response.UserUpdateFormResponse;
import com.kakao.cafe.service.user.dto.UserInfo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserViewMapper {
    public UserProfileResponse toUserProfileDto(UserInfo userInfo) {
        return UserProfileResponse.builder()
                .userName(userInfo.getUserName())
                .email(userInfo.getEmail())
                .build();
    }

    public List<UserItemResponse> toUserItemResponseList(List<UserInfo> userInfoList) {
        return userInfoList.stream()
                .map(user -> toUserItemResponse(user))
                .collect(Collectors.toList());
    }

    public UserItemResponse toUserItemResponse(UserInfo userInfo) {
        return UserItemResponse.builder()
                .id(userInfo.getId())
                .userId(userInfo.getUserId())
                .userName(userInfo.getUserName())
                .email(userInfo.getEmail()).build();
    }

    public UserUpdateFormResponse toUserUpdateFormResponse(UserInfo userInfo) {
        return UserUpdateFormResponse.builder()
                .id(userInfo.getId())
                .userId(userInfo.getUserId())
                .userName(userInfo.getUserName())
                .email(userInfo.getEmail()).build();
    }


}
