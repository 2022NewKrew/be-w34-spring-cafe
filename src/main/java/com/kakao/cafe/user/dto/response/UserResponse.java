package com.kakao.cafe.user.dto.response;

import com.kakao.cafe.user.domain.User;

public class UserResponse {
    private final String email;
    private final String nickname;
    private final String createdDate;

    public UserResponse(String email, String nickname, String createdDate) {
        this.email = email;
        this.nickname = nickname;
        this.createdDate = createdDate;
    }

    public static UserResponse of(User user) {
        return new UserResponse(user.getEmail(), user.getNickname(), user.getCreatedDate().toLocalDate().toString());
    }

    @Override
    public String toString() {
        return "UserResponse{" +
            "email='" + email + '\'' +
            ", nickname='" + nickname + '\'' +
            ", createdDate='" + createdDate + '\'' +
            '}';
    }
}
