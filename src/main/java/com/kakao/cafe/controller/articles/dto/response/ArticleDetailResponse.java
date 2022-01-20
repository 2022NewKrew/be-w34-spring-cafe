package com.kakao.cafe.controller.articles.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;


@Getter
@ToString
public class ArticleDetailResponse {
    private final Long articleId;
    private final String writerId;
    private final String writerName;
    private final String title;
    private final String contents;
    private final Boolean canUpdate;

    @Builder
    public ArticleDetailResponse(Long articleId, String writerId, String writerName, String title, String contents, Boolean canUpdate) {
        this.articleId = articleId;
        this.writerId = writerId;
        this.writerName = writerName;
        this.title = title;
        this.contents = contents;
        this.canUpdate = canUpdate;
    }
}
