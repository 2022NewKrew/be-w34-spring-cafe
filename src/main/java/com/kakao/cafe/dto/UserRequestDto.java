package com.kakao.cafe.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {
    private String stringId;
    private String password;
    private String name;
    private String email;

    public UserRequestDto(String stringId, String password, String name, String email){
        this.stringId = stringId;
        this.password = password;
        this.name = name;
        this.email = email;
    }
}
