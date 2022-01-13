package com.kakao.cafe.domain;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BaseTimeEntity {
    protected final LocalDateTime time;

    public BaseTimeEntity() {
        this.time = LocalDateTime.now();
    }
}
