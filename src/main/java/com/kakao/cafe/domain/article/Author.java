package com.kakao.cafe.domain.article;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Author {

    private final String author;

    @Override
    public String toString() {
        return author;
    }
}
