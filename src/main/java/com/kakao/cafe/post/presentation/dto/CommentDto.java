package com.kakao.cafe.post.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CommentDto {
    private final Long id;
    private final String writerName;
    private final String comment;
}
