package com.kakao.cafe.domain.user;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@ToString
@Getter
public class User {
    private final String userId;
    private final String password;
    private final String email;
    private final String registerDate;

    public User(String userId, String password, String email) {
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.registerDate = LocalDate.now().toString();
    }
}
