package com.kakao.cafe.domain;

import com.kakao.cafe.dto.UserDto;
import java.util.UUID;

public class User {

    private final UUID userId;
    private final String email;
    private final String nickname;
    private final String password;

    public User(UserDto userDto) {
        this.userId = UUID.randomUUID();
        this.email = userDto.getEmail();
        this.nickname = userDto.getNickname();
        this.password = userDto.getPassword();
    }

    public UUID getUserId() {
        return userId;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPassword() {
        return password;
    }
    
    public String getEmail() {
        return email;
    }

}
