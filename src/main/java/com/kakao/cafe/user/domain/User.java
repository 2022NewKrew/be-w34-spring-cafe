package com.kakao.cafe.user.domain;

public class User {
    private String userId;
    private String password;
    private String name;
    private String email;
    private Long sequence;

    //setter 대신 객체는 항상 생성자로만 생성하도록 구성.
    public User(String userId, String password, String name, String email) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
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

    public void setSequence(Long sequence) {
        this.sequence = sequence;
    }
}
