package com.kakao.cafe.user.domain;

import com.kakao.cafe.user.dto.UserCreateDTO;

public class User {
    private String userId;
    private String password;
    private String name;
    private String email;
    private Long sequence;

    //setter 대신 객체는 항상 생성자로만 생성하도록 구성.
    public User(UserCreateDTO userCreateDTO, Long sequence) {
        this.userId = userCreateDTO.getUserId();
        this.password = userCreateDTO.getPassword();
        this.name = userCreateDTO.getName();
        this.email = userCreateDTO.getEmail();
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
}
