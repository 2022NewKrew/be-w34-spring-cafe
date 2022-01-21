package com.kakao.cafe.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    private int id;
    private String userId;
    private String password;
    private String name;
    private String email;

    public void update(String password, String name, String email) {
        if (password != null) {
            this.password = password;
        }
        if (name != null) {
            this.name = name;
        }
        if (email != null) {
            this.email = email;
        }
    }

}
