package com.kakao.cafe.web.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserResponse {

    private final long id;
    private final String password;
    private final String userId;
    private final String email;
    private final String registerDate;

    @Builder
    public UserResponse(long id, String password, String userId, String email, String registerDate) {
        this.id = id;
        this.password = password;
        this.userId = userId;
        this.email = email;
        this.registerDate = registerDate;
    }

    public static UserResponse nonePasswordInstance(UserResponse userResponse) {
        return UserResponse.builder()
                .userId(userResponse.getUserId())
                .email(userResponse.getEmail())
                .registerDate(userResponse.getRegisterDate())
                .build();
    }
}
