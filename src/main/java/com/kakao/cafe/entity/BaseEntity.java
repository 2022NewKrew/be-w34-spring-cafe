package com.kakao.cafe.entity;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BaseEntity {
    protected LocalDateTime createdDate;
    protected LocalDateTime updatedDate;
}
