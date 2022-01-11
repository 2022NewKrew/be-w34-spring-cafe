package com.kakao.cafe.user.repository;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class CreateUserDTO {
    public String email;
    public String nickName;
    public String password;
    public LocalDateTime signUpDate;
}
