package com.kakao.cafe.domain.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class Post {
    private Long postId;
    private String writer;
    private String title;
    private String contents;
}
