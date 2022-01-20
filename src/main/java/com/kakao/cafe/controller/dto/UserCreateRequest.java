package com.kakao.cafe.controller.dto;

import com.kakao.cafe.domain.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateRequest {
    private String nickname;
    private String password;
    private String name;
    private String email;

    public User toEntity(){
        return User.builder()
                .nickname(nickname)
                .password(password)
                .name(name)
                .email(email).build();
    }

}
