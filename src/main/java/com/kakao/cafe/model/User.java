package com.kakao.cafe.model;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;
import lombok.ToString;

@ToString(exclude = "password")
public class User {

    private static final AtomicLong idGenerator;

    private final Long id;

    private final String userId;
    private final String password;
    private final String name;
    private final String email;

    private final LocalDateTime createdAt;

    static {
        idGenerator = new AtomicLong(1L);
    }

    public static User of(String userId, String password, String name, String email) {
        return new User(userId, password, name, email, LocalDateTime.now());
    }

    private User(String userId, String password, String name, String email,
        LocalDateTime createdAt) {
        this.id = idGenerator.getAndIncrement();
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
