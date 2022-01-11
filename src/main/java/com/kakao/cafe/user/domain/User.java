package com.kakao.cafe.user.domain;

import com.kakao.cafe.user.dto.SignUpDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class User {

    private String userId;

    private String password;

    private String name;

    private String email;

    public User(SignUpDTO signUpDTO){
        this.userId=signUpDTO.getUserId();
        this.password=signUpDTO.getPassword();
        this.name=signUpDTO.getName();
        this.email=signUpDTO.getEmail();
    }
}
