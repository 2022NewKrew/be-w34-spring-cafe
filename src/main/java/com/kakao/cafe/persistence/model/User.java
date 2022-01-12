package com.kakao.cafe.persistence.model;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;
import lombok.ToString;

@ToString(exclude = "password")
public class User {

    private static final AtomicLong idGenerator;

    private final Long id;

    private final String uid;
    private final String password;
    private final String name;
    private final String email;

    private final LocalDateTime createdAt;

    static {
        idGenerator = new AtomicLong(1L);
    }

    public static User of(String uid, String password, String name, String email) {
        return new User(uid, password, name, email, LocalDateTime.now());
    }

    private User(String uid, String password, String name, String email,
        LocalDateTime createdAt) {
        this.id = idGenerator.getAndIncrement();
        this.uid = uid;
        this.password = password;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean validPassword(String password) {
        return this.password.equals(password);
    }
}
