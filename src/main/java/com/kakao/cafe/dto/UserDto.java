package com.kakao.cafe.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class UserDto {
    private String userId;
    private String password;
    private String name;
    private String email;

    public UserDto(String userId, String password, String name, String email) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public String toString(){
        return String.format("{userId = %s," +
                "password = %s," +
                "name = %s," +
                "email = %s}", userId, password, name, email);
    }
}
