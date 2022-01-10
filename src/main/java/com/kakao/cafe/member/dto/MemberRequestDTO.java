package com.kakao.cafe.member.dto;

import com.kakao.cafe.member.domain.Member;

public class MemberRequestDTO {

    private String userId;
    private String password;
    private String passwordCheck;
    private String name;
    private String email;

    public MemberRequestDTO(String userId, String password, String passwordCheck, String name, String email) {
        this.userId = userId;
        this.password = password;
        this.passwordCheck = passwordCheck;
        this.name = name;
        this.email = email;
    }

    public boolean checkPassword() {
        return password.equals(passwordCheck);
    }

    public Member toMember() {
        return new Member(userId, password, name, email);
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordCheck() {
        return passwordCheck;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
