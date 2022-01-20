package com.kakao.cafe.article.application.port.in;

public class ArticleInventoryInfo {
    private final long articleId;
    private final String writerName;
    private final String title;
    private final String createdTime;

    public ArticleInventoryInfo(long articleId, String createdTime, String writerName, String title) {
        this.articleId = articleId;
        this.writerName = writerName;
        this.title = title;
        this.createdTime = createdTime;
    }
}
