package com.kakao.cafe.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class User {
    private Long id;
    private String stringId;
    private String email;
    private String nickName;
    private String password;
    private LocalDateTime signUpDate;


}
