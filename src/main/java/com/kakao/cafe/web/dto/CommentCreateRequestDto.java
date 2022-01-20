package com.kakao.cafe.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CommentCreateRequestDto {

    private final Long articleId;
    private final String commenter;
    private final String contents;
    private final LocalDateTime createdAt = LocalDateTime.now();
    private final LocalDateTime modifiedAt = LocalDateTime.now();
}
