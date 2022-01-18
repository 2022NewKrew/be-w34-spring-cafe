package com.kakao.cafe.user.service;

import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.service.dto.UserProfileServiceResponse;

public class UserServiceDTOMapper {

    public static UserProfileServiceResponse convertToUserProfileServiceResponse(User user) {
        return UserProfileServiceResponse.builder()
                .name(user.getName())
                .stringId(user.getStringId())
                .email(user.getEmail())
                .build();
    }
}
