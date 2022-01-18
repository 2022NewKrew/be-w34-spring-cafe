package com.kakao.cafe.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Users {

    private int id;
    private String userId;
    private String password;
    private String name;
    private String email;

    @Override
    public String toString() {
        return name;
    }

}
