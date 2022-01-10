package com.kakao.cafe;

import java.util.UUID;

public class User {

    private UUID user_id;
    private String nickname;
    private String password;
    private String email;

    public User(String nickname, String password, String email) {
        this.user_id = UUID.randomUUID();
        this.nickname = nickname;
        this.password = password;
        this.email = email;
    }


}
