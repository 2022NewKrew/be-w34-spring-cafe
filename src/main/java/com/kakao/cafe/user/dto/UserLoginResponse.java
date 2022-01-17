package com.kakao.cafe.user.dto;

import com.kakao.cafe.user.domain.User;
import lombok.Builder;

@Builder
public class UserLoginResponse {
    public String userId;
    public String password;
    public String name;
    public String email;

    public static UserLoginResponse valueOf(User user) {
        return UserLoginResponse.builder()
                .userId(user.getUserId())
                .password(user.getPassword())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
