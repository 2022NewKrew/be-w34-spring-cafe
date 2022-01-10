package com.kakao.cafe.domain.article;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Text {

    private final String text;

    @Override
    public String toString() {
        return text;
    }
}
