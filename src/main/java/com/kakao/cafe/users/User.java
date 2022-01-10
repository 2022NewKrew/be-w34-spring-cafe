package com.kakao.cafe.users;

import static org.springframework.beans.BeanUtils.copyProperties;

public class User {

    private String userId;

    private String password;

    private String name;

    private String email;

    public User (UserRequest userRequest) {
        copyProperties(userRequest, this);
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
