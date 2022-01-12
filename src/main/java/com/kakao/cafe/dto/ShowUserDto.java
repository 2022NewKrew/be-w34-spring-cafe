package com.kakao.cafe.dto;

public class ShowUserDto {
    private long id;
    private String userId;
    private String name;
    private String email;

    public ShowUserDto(long id, String userId, String name, String email) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
