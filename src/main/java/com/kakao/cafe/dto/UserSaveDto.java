package com.kakao.cafe.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSaveDto {
    private String userId;
    private String password;
    private String name;
    private String email;

    public UserSaveDto(String userId, String password, String name, String email){
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }
}
