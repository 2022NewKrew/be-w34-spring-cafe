package com.kakao.cafe.user.dto.response;

import com.kakao.cafe.user.domain.User;

public class UserResponse {
    private final Long id;
    private final String email;
    private final String nickname;
    private final String createdDate;

    public UserResponse(Long id, String email, String nickname, String createdDate) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.createdDate = createdDate;
    }

    public static UserResponse of(User user) {
        return new UserResponse(user.getId(), user.getEmail(), user.getNickname(), user.getCreatedDate().toLocalDate().toString());
    }

    @Override
    public String toString() {
        return "UserResponse{" +
            "id=" + id +
            ", email='" + email + '\'' +
            ", nickname='" + nickname + '\'' +
            ", createdDate='" + createdDate + '\'' +
            '}';
    }
}
