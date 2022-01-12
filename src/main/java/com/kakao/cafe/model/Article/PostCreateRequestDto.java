package com.kakao.cafe.model.Article;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class PostCreateRequestDto {
    private final String title;
    private final String content;

    public Post toPost() {
        return new Post(this);
    }
}
