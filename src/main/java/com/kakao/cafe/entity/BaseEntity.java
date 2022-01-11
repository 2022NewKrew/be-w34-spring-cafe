package com.kakao.cafe.entity;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

public abstract class BaseEntity {
    protected Long id;
    protected final LocalDateTime createdTime;

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
