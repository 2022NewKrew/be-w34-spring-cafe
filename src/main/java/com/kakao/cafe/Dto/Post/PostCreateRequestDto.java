package com.kakao.cafe.Dto.Post;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class PostCreateRequestDto {
    private final Long authorId;
    private final String title;
    private final String content;
}
