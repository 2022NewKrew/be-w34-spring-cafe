package com.kakao.cafe.domain.user;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@ToString
@Getter
public class User {
    private final long id;
    private final String userId;
    private final String password;
    private final String email;
    private final String registerDate;

    public User(long id,String userId, String password, String email) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.registerDate = LocalDate.now().toString();
    }

    public User(long id, String userId, String password, String email, String registerDate) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.registerDate = registerDate;
    }
}
