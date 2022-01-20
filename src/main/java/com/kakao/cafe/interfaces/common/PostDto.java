package com.kakao.cafe.interfaces.common;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
    private String writer;
    private String title;
    private String body;
    private String createdAt;
    private Boolean isRemoved;

    @Builder
    public PostDto(String writer, String title, String body) {
        this.writer = writer;
        this.title = title;
        this.body = body;
    }
}
