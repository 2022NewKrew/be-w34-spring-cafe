package com.kakao.cafe.domain.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private int id = 0;
    private String userId;
    private String password;
    private String name;
    private String email;

    public User(String userId, String password, String name, String email) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
        id++;
    }

    public void updateUser(User changedUser){
        this.setId(changedUser.getId());
        this.setUserId(changedUser.getUserId());
        this.setEmail(changedUser.getEmail());
        this.setName(changedUser.getName());
        this.setPassword(changedUser.getPassword());
    }
}
