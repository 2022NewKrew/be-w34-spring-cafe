package com.kakao.cafe.entity;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BaseEntity {
    protected LocalDateTime createdDate;
    protected LocalDateTime updatedDate;

    public void putCreatedDate() {
        this.createdDate = LocalDateTime.now();
    }

    public void putUpdatedDate() {
        this.updatedDate = LocalDateTime.now();
    }
}
