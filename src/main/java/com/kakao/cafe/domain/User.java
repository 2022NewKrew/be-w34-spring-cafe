package com.kakao.cafe.domain;

import com.kakao.cafe.dto.CreateUserDto;
import com.kakao.cafe.dto.ShowUserDto;

import java.util.concurrent.atomic.AtomicInteger;

public class User {
    private static final AtomicInteger userCnt = new AtomicInteger(3);
    private final long id;
    private final String userId;
    private final String password;
    private final String name;
    private final String email;

    public User(String userId, String password, String name, String email) {
        id = userCnt.get();
        userCnt.set((int) (id + 1));
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public User(CreateUserDto createUserDto) {
        id = userCnt.get();
        userCnt.set((int) (id + 1));
        this.userId = createUserDto.getUserId();
        this.password = createUserDto.getPassword();
        this.name = createUserDto.getName();
        this.email = createUserDto.getEmail();
    }

    public ShowUserDto toShowUserDto() {
        return new ShowUserDto(id, userId, name, email);
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
