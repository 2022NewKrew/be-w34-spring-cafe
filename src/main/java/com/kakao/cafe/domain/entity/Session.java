package com.kakao.cafe.domain.entity;

import com.kakao.cafe.service.dto.SessionDto;

public class Session {

    private final String id;
    private final long userId;

    public Session(String id, long userId) {
        this.id = id;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public SessionDto toDto() {
        return new SessionDto(id, userId);
    }
}
