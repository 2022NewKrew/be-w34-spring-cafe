package com.kakao.cafe.user.dto.response;

import com.kakao.cafe.user.domain.User;
import lombok.Builder;
import lombok.ToString;

@ToString
public class UserResponse {

    private final Long id;
    private final String email;
    private final String nickname;
    private final String createdDate;

    @Builder
    private UserResponse(Long id, String email, String nickname, String createdDate) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.createdDate = createdDate;
    }

    public static UserResponse of(User user) {
        return UserResponse.builder()
            .id(user.getId())
            .email(user.getEmail())
            .nickname(user.getNickname())
            .createdDate(user.getCreatedDate().toLocalDate().toString())
            .build();
    }

    public Long getId() {
        return id;
    }
}
