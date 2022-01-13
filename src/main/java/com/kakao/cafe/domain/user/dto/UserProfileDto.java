package com.kakao.cafe.domain.user.dto;

import com.kakao.cafe.domain.user.User;

public class UserProfileDto {
    private final String userId;
    private final String password;
    private final String nickname;
    private final String email;

    private UserProfileDto(String userId, String password, String nickname, String email) {
        this.userId = userId;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public User toEntity() {
        return new User(null, getUserId(), getPassword(), getNickname(), getEmail(), null);
    }
}
