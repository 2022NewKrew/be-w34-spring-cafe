package com.kakao.cafe.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private String email;
    private String username;
    private String password;
    private LocalDateTime regDate;
    private LocalDateTime modDate;

    public void changeUsername(String username) {
        this.username = username;
    }

    public void changePassword(String password) {
        this.password = password;
    }
}
