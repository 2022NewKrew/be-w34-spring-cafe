package com.kakao.cafe.domain.user;

import lombok.Getter;

@Getter
public class User {
    private int id;
    private String stringId;
    private String password;
    private String name;
    private String email;

    public User(String stringId, String password, String name, String email){
        this.stringId = stringId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public void update(User user){
        this.password = user.getPassword();
        this.name = user.getName();
        this.email = user.getEmail();
    }

    public void setId(int id){
        this.id = id;
    }
}
