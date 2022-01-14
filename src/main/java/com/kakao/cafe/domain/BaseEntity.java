package com.kakao.cafe.domain;

import java.time.LocalDateTime;

public abstract class BaseEntity {
    protected Long id;
    protected final LocalDateTime createdTime;

    public BaseEntity(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public BaseEntity(Long id, LocalDateTime createdTime) {
        this.id = id;
        this.createdTime = createdTime;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public Long getId() {
        return id;
    }
}
