package com.kakao.cafe.user.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class User {
    private Long id;
    private String stringId;
    private String email;
    private String nickName;
    private String password;
    private LocalDateTime signUpDate;


    @Builder
    private User(Long id, String stringId, String email, String nickName, String password, LocalDateTime signUpDate) {
        this.id = id;
        this.stringId = stringId;
        this.email = email;
        this.nickName = nickName;
        this.password = password;
        this.signUpDate = signUpDate;
    }
}
