package com.kakao.cafe.dto.article;

public class ArticleShowForm {
    private final long articleId;
    private final String writerName;
    private final String title;
    private final int numberOfReply;
    private final String createdTime;

    public ArticleShowForm(long articleId, String writerName, String title, int numberOfReply, String createdTime) {
        this.articleId = articleId;
        this.writerName = writerName;
        this.title = title;
        this.numberOfReply = numberOfReply;
        this.createdTime = createdTime;
    }
}
