package com.kakao.cafe.domain.article;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Title {

    private final String title;

    @Override
    public String toString() {
        return title;
    }
}
