package com.kakao.cafe.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {
    private final UserId userId;
    private Password password;
    private Name name;
    private Email email;

    public boolean equalId(UserId userId) {
        return this.userId.equals(userId);
    }

    public boolean equalPassword(User user) {
        return this.password.equals(user.password);
    }
}
