package com.kakao.cafe.post.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CommentRequest {
    private String writerName;
    private String comment;
}
