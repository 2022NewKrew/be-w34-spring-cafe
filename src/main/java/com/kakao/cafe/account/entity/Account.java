package com.kakao.cafe.account.entity;

import com.kakao.cafe.account.dto.AccountDto;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Account {
    private final String userId;
    private final String name;
    private final String password;
    private final String email;

    @Builder
    public Account(String userId, String name, String password, String email) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public AccountDto toDto() {
        return AccountDto.builder()
                .userId(userId)
                .password(password)
                .name(name)
                .email(email).build();
    }
}
