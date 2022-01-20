package com.kakao.cafe.controller.articles.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ArticleUpdateFormResponse {
    private final Long articleId;
    private final String writerId;
    private final String writerName;
    private final String title;
    private final String contents;

    @Builder
    public ArticleUpdateFormResponse(Long articleId, String writerId, String writerName, String title, String contents) {
        this.articleId = articleId;
        this.writerId = writerId;
        this.writerName = writerName;
        this.title = title;
        this.contents = contents;
    }
}
