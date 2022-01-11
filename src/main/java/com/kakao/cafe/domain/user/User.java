package com.kakao.cafe.domain.user;

import com.kakao.cafe.dto.UserRequestDto;
import lombok.Getter;

@Getter
public class User {
    private int id;
    private String stringId;
    private String password;
    private String name;
    private String email;

    public User(int id, UserRequestDto userRequestDto){
        this.id = id;
        this.stringId = userRequestDto.getStringId();
        this.password = userRequestDto.getPassword();
        this.name = userRequestDto.getName();
        this.email = userRequestDto.getEmail();
    }

    public void update(UserRequestDto userRequestDto){
        this.stringId = userRequestDto.getStringId();
        this.password = userRequestDto.getPassword();
        this.name = userRequestDto.getName();
        this.email = userRequestDto.getEmail();
    }
}
