package com.kakao.cafe.post.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Getter
public class PostRequest {
    private final String title;
    private final String content;
    private String writerName;

    public void setWriterName(String writerName) {
        this.writerName = writerName;
    }
}
