package com.kakao.cafe.domain.user;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class User {
    @Builder.Default
    private int id = -1;
    private String stringId;
    private String password;
    private String name;
    private String email;

    public void changePassword(String password){
        this.password = password;
    }

    public void changeName(String name){
        this.name = name;
    }

    public void changeEmail(String email){
        this.email = email;
    }

    public Boolean isNew(){
        return id == -1;
    }
}
