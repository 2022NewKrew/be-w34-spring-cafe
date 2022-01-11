package com.kakao.cafe.user.domain;

import lombok.Getter;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicLong;

@Getter
@ToString
public class User {

    private static final AtomicLong nextId = new AtomicLong();

    private final long id;
    private final String userId;
    private final String password;
    private final String name;
    private final String email;

    private User(String userId, String password, String name, String email) {
        this.id = nextId.incrementAndGet();
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public static User of(String userId, String password, String name, String email) {
        return new User(userId, password, name, email);
    }
}
