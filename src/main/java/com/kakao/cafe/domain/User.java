package com.kakao.cafe.domain;

import com.kakao.cafe.vo.UserDto;
import lombok.Getter;

@Getter
public class User {

    private Long id;
    private String userId;
    private String password;
    private String name;
    private String email;

    public User(Long id, UserDto userDto){
        this.id = id;
        this.userId = userDto.getUserId();
        this.password = userDto.getPassword();
        this.name = userDto.getName();
        this.email = userDto.getEmail();
    }

}
