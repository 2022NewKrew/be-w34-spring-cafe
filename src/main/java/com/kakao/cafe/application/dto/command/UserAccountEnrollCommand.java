package com.kakao.cafe.application.dto.command;

import com.kakao.cafe.domain.user.UserAccount;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
public class UserAccountEnrollCommand {

    private final String password;
    private final String username;
    private final String email;

    public UserAccount toEntity() {
        return UserAccount.builder()
                .email(email)
                .username(username)
                .password(password)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
