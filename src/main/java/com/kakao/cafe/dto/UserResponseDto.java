package com.kakao.cafe.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    private int id;
    private String stringId;
    private String name;
    private String email;

    public UserResponseDto(int id, String stringId , String name, String email){
        this.id = id;
        this.stringId = stringId;
        this.name = name;
        this.email = email;
    }
}
