package com.kakao.cafe.article.application.port.in;

public class ArticlePostInfo {
    private final long articleId;
    private final String createdTime;
    private final String writerName;
    private final String title;
    private final String contents;
    private final int numberOfReply;

    public ArticlePostInfo(long articleId, String createdTime, String writerName, String title, String contents, int numberOfReply) {
        this.articleId = articleId;
        this.createdTime = createdTime;
        this.writerName = writerName;
        this.title = title;
        this.contents = contents;
        this.numberOfReply = numberOfReply;
    }
}
