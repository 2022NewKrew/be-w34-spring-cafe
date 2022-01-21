package com.kakao.cafe.dto.article;

public class ArticleContents {
    private final long articleId;
    private final String time;
    private final String writer;
    private final String writerId;
    private final String title;
    private final String contents;

    public ArticleContents(long articleId, String time, String writer, String writerId, String title, String contents) {
        this.articleId = articleId;
        this.time = time;
        this.writer = writer;
        this.writerId = writerId;
        this.title = title;
        this.contents = contents;
    }

    public long getArticleId() { return articleId; }

    public String getTime() {
        return time;
    }

    public String getWriter() { return writer; }

    public String getWriterId() { return writerId; }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }
}
