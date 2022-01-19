package com.kakao.cafe.user.persistence;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class User {

    private Long id;
    private String userId;
    private String password;
    private String name;
    private String email;

    public static User of(Long id, String userId, String password, String name, String email) {
        return new User(id, userId, password, name, email);
    }

    public boolean validPassword(String password) {
        return this.password.equals(password);
    }
}
