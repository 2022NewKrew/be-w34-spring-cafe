package com.kakao.cafe.domain.user.dto;

import com.kakao.cafe.domain.user.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserResponseDto {
    private final String userId;
    private final String nickname;
    private final String email;

    private UserResponseDto(String userId, String nickname, String email) {
        this.userId = userId;
        this.nickname = nickname;
        this.email = email;
    }

    public static UserResponseDto of(User user) {
        return new UserResponseDto(user.getUserId(), user.getNickname(), user.getEmail());
    }

    public static List<UserResponseDto> of(List<User> users) {
        return users.stream()
                .map(UserResponseDto::of)
                .collect(Collectors.toList());
    }

    public String getUserId() {
        return userId;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }
}
