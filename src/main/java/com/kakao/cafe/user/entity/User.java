package com.kakao.cafe.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter @Setter
public class User {
    private Long id;
    private String userId;
    private String password;
    private String name;
    private String email;
    private LocalDateTime createdAt;

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }
}
