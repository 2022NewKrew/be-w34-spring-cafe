package com.kakao.cafe.domain;

import com.kakao.cafe.dto.MemberCreateDto;

import java.time.LocalDate;

public class Member {
    private int id;
    private String userId;
    private String password;
    private String email;
    private String name;
    private LocalDate createdAt;

    public Member() {
    }

    public Member(int id, String userId, String password, String email, String name, LocalDate createdAt) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.name = name;
        this.createdAt = createdAt;
    }

    public Member(String userId, String password, String email, String name, LocalDate createdAt) {
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.name = name;
        this.createdAt = createdAt;
    }

    public static Member of(MemberCreateDto memberCreateDto) {
        return new Member(memberCreateDto.getUserId(),
                memberCreateDto.getPassword(),
                memberCreateDto.getEmail(),
                memberCreateDto.getName(),
                LocalDate.now());
    }

    public int getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return name + "/" + password + "/" + email + "/" + userId;
    }
}
