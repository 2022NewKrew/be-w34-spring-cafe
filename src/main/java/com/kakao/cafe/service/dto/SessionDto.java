package com.kakao.cafe.service.dto;

import com.kakao.cafe.domain.entity.Session;

public class SessionDto {

    private final String id;
    private final long userId;

    public SessionDto(String id, long userId) {
        this.id = id;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public Session toEntity() {
        return new Session(id, userId);
    }
}
