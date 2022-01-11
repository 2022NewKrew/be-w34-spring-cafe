package com.kakao.cafe.entity;

import java.time.LocalDateTime;

public abstract class BaseEntity {
    protected final LocalDateTime createdTime;
    protected Long id;

    public BaseEntity(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
