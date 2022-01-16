package com.kakao.cafe.user.application.port.in;

import com.kakao.cafe.user.domain.Email;
import com.kakao.cafe.user.domain.UserId;

public class FindUserDto {

    private final UserId userId;
    private final Email email;
    private final String nickname;

    public FindUserDto(UserId userId, Email email, String nickname) {
        this.userId = userId;
        this.email = email;
        this.nickname = nickname;
    }

    public UserId getUserId() {
        return userId;
    }

    public Email getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }
}
