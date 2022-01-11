package com.kakao.cafe.dto.article;

public class ArticleShowFormWithContents extends ArticleShowForm {
    private final String contents;

    public ArticleShowFormWithContents(long articleId, String writerName, String title, int numberOfReply, String createdTime, String contents) {
        super(articleId, writerName, title, numberOfReply, createdTime);
        this.contents = contents;
    }
}
