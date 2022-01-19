package com.kakao.cafe.Dto.Post;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PostCreateRequestDto {
    private final String title;
    private final String content;

    public PostCreateRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
