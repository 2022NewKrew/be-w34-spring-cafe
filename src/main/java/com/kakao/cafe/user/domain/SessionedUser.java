package com.kakao.cafe.user.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.util.Objects;

@Builder
@Getter
public class SessionedUser {
    private String userId;
    private String password;
    private String name;
    private String email;

    public static SessionedUser valueOf(User user) {
        return SessionedUser.builder()
                .userId(user.getUserId())
                .password(user.getPassword())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    public boolean hasSameUserLoggedIn(String userId) {
        return Objects.equals(userId, this.userId);
    }

    public boolean matchesPassword(String password) {
        return Objects.equals(password, this.password);
    }
}
