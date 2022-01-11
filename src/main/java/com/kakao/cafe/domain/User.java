package com.kakao.cafe.domain;

import com.kakao.cafe.dto.UserDto;
import lombok.Getter;

import java.util.Date;

@Getter
public class User {
    private int id;
    private Date joinedAt;
    private String userId;
    private String password;
    private String name;
    private String email;

    public User(int id, UserDto userDto) {
        this.id = id;
        this.userId = userDto.getUserId();
        this.password = userDto.getPassword();
        this.name = userDto.getName();
        this.email = userDto.getEmail();
        this.joinedAt = new Date();
    }

}
