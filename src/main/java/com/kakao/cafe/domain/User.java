package com.kakao.cafe.domain;

import lombok.*;

@Getter
@NoArgsConstructor
public class User {
    private Long id;
    private String nickname;
    private String password;
    private String name;
    private String email;

    @Builder
    public User(Long id, String nickname, String password, String name, String email) {
        this.id = id;
        this.nickname = nickname;
        this.password = password;
        this.name = name;
        this.email = email;
    }
}
