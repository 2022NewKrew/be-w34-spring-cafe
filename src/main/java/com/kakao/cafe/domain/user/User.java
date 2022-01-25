package com.kakao.cafe.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class User {
    private final long id;
    private final String userId;
    private final String password;
    private final String email;
    private final String registerDate;

    @Builder
    private User(long id, String userId, String password, String email, String registerDate) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.registerDate = registerDate;
    }

    public boolean isPasswordMatching(String password) {
        return this.password.equals(password);
    }
}
