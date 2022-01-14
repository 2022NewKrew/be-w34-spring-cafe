package com.kakao.cafe.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class User {
    private String userId;
    private String password;
    private String email;
    private String profileImage;

    public User(String userId, String password, String email) {
        this.userId = userId;
        this.password = password;
//        this.password = hash(password);
        this.email = email;
    }

//    public String hash(String password) {
//        return passwordEncoder.encode(password);
//    }

    public String getUserId() {
        return userId;
    }

    public String getEmail() { return email;}

    public String getPassword() {
        return password;
    }
}
