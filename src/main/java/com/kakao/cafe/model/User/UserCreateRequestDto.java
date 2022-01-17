package com.kakao.cafe.model.User;

import lombok.Getter;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicLong;

@Getter
@ToString
public class UserCreateRequestDto {
    private final Long id;
    private final String userId;
    private final String nickname;
    private final String email;
    private final String password;
    private static final AtomicLong sequenceId = new AtomicLong(1);

    public UserCreateRequestDto(String userId, String nickname, String email, String password) {
        this.userId = userId;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.id = sequenceId.incrementAndGet();
    }
}
