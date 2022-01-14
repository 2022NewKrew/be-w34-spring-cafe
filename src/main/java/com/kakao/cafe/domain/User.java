package com.kakao.cafe.domain;

import com.kakao.cafe.dto.UserDto;

public class User {

    private final UserId userId;
    private final String email;
    private final String nickname;
    private final String password;

    public User(UserDto userDto) {
        this.userId = UserId.create();
        this.email = userDto.getEmail();
        this.nickname = userDto.getNickname();
        this.password = userDto.getPassword();
    }

    public UserId getUserId() {
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
