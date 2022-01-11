package com.kakao.cafe.model;

import com.kakao.cafe.dto.UserRegisterRequest;
import java.util.UUID;

public class User {

    private final UUID id;
    private final String userId;
    private final String password;
    private final String name;
    private final String email;

    public User(String userId, String password, String name, String email) {
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public static User of(UserRegisterRequest requestDto) {
        return new User(requestDto.getUserId(), requestDto.getPassword(), requestDto.getName(), requestDto.getEmail());
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }
}
