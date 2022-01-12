package com.kakao.cafe.domain;

import com.kakao.cafe.dto.UserDto;
import lombok.Getter;

import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;

import java.util.Date;

@Getter
public class User {
    private int id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
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


    public String getJoinedAt(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(joinedAt);
    }

}
