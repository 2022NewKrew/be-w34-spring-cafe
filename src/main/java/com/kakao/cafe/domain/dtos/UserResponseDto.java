package com.kakao.cafe.domain.dtos;

import com.kakao.cafe.domain.User;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UserResponseDto {
    private final long id;
    private final String email;
    private final String name;
    private final String creationTime;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
        this.creationTime = dateToString(user.getCreationTime());
    }

    private String dateToString(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    public long getId() {
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
