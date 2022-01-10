package com.kakao.cafe.users;

import org.springframework.lang.NonNull;

import static org.springframework.beans.BeanUtils.copyProperties;

public class User {

    @NonNull
    private String userId;
    @NonNull
    private String password;
    @NonNull
    private String name;
    @NonNull
    private String email;

    public User (@NonNull UserRequest userRequest) {
        this.userId = userRequest.getUserId();
        this.password = userRequest.getPassword();
        this.name = userRequest.getName();
        this.email = userRequest.getEmail();
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
