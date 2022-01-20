package com.kakao.cafe.article.application.port.in;

import lombok.Getter;

@Getter
public class ArticleUpdateForm {
    private final Long articleId;
    private final String writerName;
    private final String title;
    private final String contents;

    public ArticleUpdateForm(Long articleId, String writerName, String title, String contents) {
        this.articleId = articleId;
        this.writerName = writerName;
        this.title = title;
        this.contents = contents;
    }
}
