package com.kakao.cafe.user.domain;

import com.kakao.cafe.user.dto.UserCreateDTO;

public class User {
    private String userId;
    private String password;
    private String name;
    private String email;
    private Long sequence;

    //객체는 항상 생성자로만 생성하도록 구성.
    public User(String userid, String password, String name, String email, long sequence) {
        this.userId = userid;
        this.password = password;
        this.name = name;
        this.email = email;
        this.sequence = sequence;
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

    public Long getSequence() {
        return sequence;
    }

    public Boolean equalsUserId(String userId){
        return this.userId.equals(userId);
    }
}
