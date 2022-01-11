package com.kakao.cafe.post.presentation.dto;

import lombok.Getter;

@Getter
public class PostDto {

    private final Long id;
    private final String title;
    private final String writerName;
    private final String summary;

    public PostDto(Long id, String title, String writerName, String summary) {
        this.id = id;
        this.title = title;
        this.writerName = writerName;
        this.summary = summary;
    }
}
