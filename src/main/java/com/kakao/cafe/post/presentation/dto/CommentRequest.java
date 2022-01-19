package com.kakao.cafe.post.presentation.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Getter
public class CommentRequest {
    private String writerName;
    private final String comment;

    public void setWriterName(String writerName) {
        this.writerName = writerName;
    }
}
