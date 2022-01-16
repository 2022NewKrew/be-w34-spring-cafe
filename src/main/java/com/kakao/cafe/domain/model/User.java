package com.kakao.cafe.domain.model;

import com.kakao.cafe.domain.dto.UserSignUpDTO;
import lombok.Getter;

@Getter
public class User {
    private String userId;
    private String password;
    private String name;
    private String email;

    public User(String userId, String password, String name, String email) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }


    public User(UserSignUpDTO userSignUpDTO) {
        this.userId = userSignUpDTO.getUserId();
        this.password = userSignUpDTO.getPassword();
        this.name = userSignUpDTO.getName();
        this.email = userSignUpDTO.getEmail();
    }
}



