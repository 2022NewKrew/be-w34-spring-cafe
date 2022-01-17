package com.kakao.cafe.user.service;

import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.service.dto.UserProfileServiceResponse;
import org.springframework.stereotype.Component;

@Component
public class UserServiceDTOMapper {

    public static UserProfileServiceResponse convertToUserProfileServiceResponse(User user) {
        UserProfileServiceResponse profile = UserProfileServiceResponse.builder()
                .name(user.getName())
                .stringId(user.getStringId())
                .email(user.getEmail())
                .build();
        return profile;
    }
}
