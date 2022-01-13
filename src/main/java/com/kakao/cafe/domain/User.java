package com.kakao.cafe.domain;

import com.kakao.cafe.dto.user.SignUpDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
public class User {

    private Long id;

    private String userId;

    private String password;

    private String name;

    private String email;

    public User(Long id, SignUpDTO signUpDTO){
        this.id = id;
        this.userId = signUpDTO.getUserId();
        this.password = signUpDTO.getPassword();
        this.name = signUpDTO.getName();
        this.email = signUpDTO.getEmail();
    }
}
