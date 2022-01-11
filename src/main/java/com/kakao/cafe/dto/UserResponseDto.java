package com.kakao.cafe.dto;

public class UserResponseDto {
    private Long id;
    private String userId;
    private String name;
    private String email;

    public static UserResponseDto of(Long id, String userId, String name, String email) {
        return new UserResponseDto(id, userId, name, email);
    }

    public UserResponseDto(Long id, String userId, String name, String email) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
