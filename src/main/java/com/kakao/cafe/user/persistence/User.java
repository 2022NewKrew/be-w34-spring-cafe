package com.kakao.cafe.user.persistence;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
public class User {

    private Long id;
    private String userId;
    private String password;
    private String name;
    private String email;

    public boolean validPassword(String password) {
        return this.password.equals(password);
    }
}
