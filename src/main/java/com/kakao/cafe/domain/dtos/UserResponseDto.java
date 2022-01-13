package com.kakao.cafe.domain.dtos;

import com.kakao.cafe.domain.User;

import java.text.SimpleDateFormat;

public class UserResponseDto {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private final Long id;
    private final String email;
    private final String name;
    private final String creationTime;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
        this.creationTime = dateFormat.format(user.getCreationTime());
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getCreationTime() {
        return creationTime;
    }
}
