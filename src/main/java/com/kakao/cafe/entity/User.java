package com.kakao.cafe.entity;

import com.kakao.cafe.dto.UserRequestDto;

import java.time.LocalDate;

public class User {
    private int id;
    private String password;
    private String name;
    private String email;
    private LocalDate createdTime;

    public User(int id, String password, String name, String email, LocalDate createdTime) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.email = email;
        this.createdTime = createdTime;
    }

    public User(UserRequestDto userRequestDto) {
        this.password = userRequestDto.getPassword();
        this.name = userRequestDto.getName();
        this.email = userRequestDto.getEmail();
        this.createdTime = LocalDate.now();
    }

    @Override
    public String toString() {
        return "User{" +
                "password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getCreatedTime() {
        return createdTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCreatedTime(LocalDate createdTime) {
        this.createdTime = createdTime;
    }
}
