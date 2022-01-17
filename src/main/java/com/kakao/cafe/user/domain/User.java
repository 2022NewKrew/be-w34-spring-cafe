package com.kakao.cafe.user.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class User {
    private Long id;
    private String stringId;
    private String email;
    private String name;
    private String password;
    private LocalDateTime signUpDate;

    @Builder
    private User(Long id, String stringId, String email, String name, String password, LocalDateTime signUpDate) {
        this.id = id;
        this.stringId = stringId;
        this.email = email;
        this.name = name;
        this.password = password;
        this.signUpDate = signUpDate;
    }
}
