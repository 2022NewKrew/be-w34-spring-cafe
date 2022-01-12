package com.kakao.cafe.adapter.out.infra.persistence.user;

import com.kakao.cafe.domain.user.User;

public class UserInfoEntity {

    private final String userId;
    private final String password;
    private final String name;
    private final String email;

    public UserInfoEntity(String userId, String password, String name, String email) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    static UserInfoEntity from(User user) {
        return new UserInfoEntity(
            user.getUserId(),
            user.getPassword(),
            user.getName(),
            user.getEmail()
        );
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
