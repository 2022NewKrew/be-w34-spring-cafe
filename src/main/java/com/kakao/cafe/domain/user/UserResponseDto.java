package com.kakao.cafe.domain.user;

import java.time.format.DateTimeFormatter;

public class UserResponseDto {
    private final Long id;
    private final String email;
    private final String name;
    private final String creationTime;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
        this.creationTime = user.getCreationTime().format(DateTimeFormatter.ISO_DATE);
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
