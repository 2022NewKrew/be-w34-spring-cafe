package com.kakao.cafe.user.dto;


import lombok.Getter;


@Getter
public class SignUpDTO {

    private String userId;

    private String password;

    private String name;

    private String email;

    public SignUpDTO(String userId,String password,String name,String email){
        this.userId=userId;
        this.password=password;
        this.name=name;
        this.email=email;
    }

}
