package com.kakao.cafe.domain;

import com.kakao.cafe.dto.CreateUserDto;
import java.util.UUID;

public class User {

    private UUID userId;
    private String email;
    private String nickname;
    private String password;

    public User(CreateUserDto createUserDto) {
        this.userId = UUID.randomUUID();
        this.email = createUserDto.getEmail();
        this.nickname = createUserDto.getNickname();
        this.password = createUserDto.getPassword();
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
