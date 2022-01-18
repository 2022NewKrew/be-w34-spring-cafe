package com.kakao.cafe.domain;

import com.kakao.cafe.dto.CreateUserDto;
import com.kakao.cafe.util.SHA256;

public class User {
    private final String userId;
    private final String password;
    private final String name;
    private final String email;
    private long id;


    public User(long id, String userId, String password, String name, String email) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public User(CreateUserDto createUserDto) {
        this.userId = createUserDto.getUserId();
        this.password = SHA256.encrypt(createUserDto.getPassword());
        this.name = createUserDto.getName();
        this.email = createUserDto.getEmail();
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}
