package com.kakao.cafe.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RequestUserDto {
    private String userId;
    private String password;
    private String prevPassword;
    private String name;
    private String email;

    public RequestUserDto(String userId, String password, String name, String email) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

}
