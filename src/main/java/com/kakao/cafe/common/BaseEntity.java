package com.kakao.cafe.common;

import java.time.LocalDateTime;

public abstract class BaseEntity {
    protected final LocalDateTime createdTime;
    protected Long id;

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
