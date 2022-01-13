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

    public User update(User user){
        this.password = user.getPassword();
        this.name = user.getName();
        this.email = user.getEmail();
        return this;
    }

    public Boolean isNew(){
        return id == -1;
    }
}
