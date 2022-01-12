package com.kakao.cafe.user.dto;

import com.kakao.cafe.user.domain.User;
import lombok.Builder;

@Builder
public class UserProfileResponse {

    public String userId;
    public String name;
    public String email;

    public static UserProfileResponse valueOf(User user) {
        return UserProfileResponse.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
