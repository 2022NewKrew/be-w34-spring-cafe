package com.kakao.cafe.domain;

import lombok.Getter;

@Getter
public class Id {
    private Long id;

    public Id() {
        id = 0L;
    }

    public Id(Long id) {
        this.id = id;
    }
}
