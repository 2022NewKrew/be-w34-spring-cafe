package com.kakao.cafe.user.dto;

import com.kakao.cafe.user.domain.User;
import lombok.Builder;

@Builder
public class SessionedUser {
    public String userId;
    public String password;
    public String name;
    public String email;

    public static SessionedUser valueOf(User user) {
        return SessionedUser.builder()
                .userId(user.getUserId())
                .password(user.getPassword())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
