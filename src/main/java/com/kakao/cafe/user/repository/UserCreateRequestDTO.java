package com.kakao.cafe.user.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class UserCreateRequestDTO {
    public String stringId;
    public String email;
    public String nickName;
    public String password;
    public LocalDateTime signUpDate;
}
