package com.kakao.cafe.dto.response;

public class UserResDto {
    private Long id;
    private String userId;
    private String name;
    private String email;

    public UserResDto(Long id, String userId, String name, String email) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    public Long getId() {
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
