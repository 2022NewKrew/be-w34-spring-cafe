package com.kakao.cafe.user.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Builder
@Getter
@ToString(exclude = {"password"})
public class User {
    private final Long id;
    private final String username;
    private final String password;
    private final String email;
    private final String displayName;
    private final String status;
    private final LocalDateTime createdAt;
    private final LocalDateTime lastModifiedAt;

    public boolean isPasswordMatched(String password) {
        return this.password.equals(password);
    }
}
