package com.kakao.cafe.domain.user.dto;

import com.kakao.cafe.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserSignUpDto {
    private final String email;
    private final String password;
    private final String nickname;

    public User toEntity() {
        return User.builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .build();
    }
}
