package com.kakao.cafe.domain;

import com.kakao.cafe.dto.UserCreateDto;

import java.time.LocalDate;

public class User {
    // 다음 생성하는 row 의 id 값을 저장하고 있는 변수
    private static int idSeq = 1;
    private int id;
    private String userId;
    private String password;
    private String email;
    private String name;
    private LocalDate createdAt;

    public User(int id, String userId, String password, String email, String name, LocalDate createdAt) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.name = name;
        this.createdAt = createdAt;
    }

    public static User of(UserCreateDto userCreateDto) {
        return new User(idSeq++,
                userCreateDto.getUserId(),
                userCreateDto.getPassword(),
                userCreateDto.getEmail(),
                userCreateDto.getName(),
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
}
