package com.kakao.cafe.service.article.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ArticleInfo {
    private final Long id;
    private final String writerId;
    private final String writerName;
    private final String title;
    private final String contents;

    @Builder
    public ArticleInfo(Long id, String writerId, String writerName, String title, String contents) {
        this.id = id;
        this.writerId = writerId;
        this.writerName = writerName;
        this.title = title;
        this.contents = contents;
    }
}
