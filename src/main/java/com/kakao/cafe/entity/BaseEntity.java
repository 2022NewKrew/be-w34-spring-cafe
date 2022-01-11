package com.kakao.cafe.entity;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
abstract class BaseEntity {
    private LocalDateTime regDate;
    private LocalDateTime modDate;

    public void register() {
        this.modDate = this.regDate = LocalDateTime.now();
    }

    public void update() {
        this.modDate = LocalDateTime.now();
    }
}
