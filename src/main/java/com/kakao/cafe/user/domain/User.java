package com.kakao.cafe.user.domain;

import com.kakao.cafe.user.dto.SignUpDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class User {

    private int id;

    private String userId;

    private String password;

    private String name;

    private String email;

    public User(int id, SignUpDTO signUpDTO) {
        this.id = id;
        this.userId = signUpDTO.getUserId();
        this.password = signUpDTO.getPassword();
        this.name = signUpDTO.getName();
        this.email = signUpDTO.getEmail();
    }
}
