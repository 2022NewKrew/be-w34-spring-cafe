package com.kakao.cafe.controller.users.mapper;

import com.kakao.cafe.controller.users.dto.response.UserItemResponse;
import com.kakao.cafe.controller.users.dto.response.UserProfileResponse;
import com.kakao.cafe.controller.users.dto.response.UserUpdateFormResponse;
import com.kakao.cafe.service.user.dto.UserInfo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class UserViewMapper {
    public UserProfileResponse toUserProfileDto(UserInfo userInfo) {
        return UserProfileResponse.builder()
                .userName(userInfo.getUserName())
                .email(userInfo.getEmail())
                .build();
    }

    public List<UserItemResponse> toUserItemResponseList(List<UserInfo> userInfoList) {
        return IntStream.range(0, userInfoList.size())
                .filter(seq -> seq < userInfoList.size())
                .mapToObj(seq -> toUserItemResponse(seq + 1, userInfoList.get(seq)))
                .collect(Collectors.toList());
    }

    public UserItemResponse toUserItemResponse(int seq, UserInfo userInfo) {
        return UserItemResponse.builder()
                .seq(seq)
                .userId(userInfo.getUserId())
                .userName(userInfo.getUserName())
                .email(userInfo.getEmail()).build();
    }

    public UserUpdateFormResponse toUserUpdateFormResponse(UserInfo userInfo) {
        return UserUpdateFormResponse.builder()
                .userId(userInfo.getUserId())
                .userName(userInfo.getUserName())
                .email(userInfo.getEmail()).build();
    }


}
