package com.kakao.cafe.dto.article;

import com.kakao.cafe.domain.entity.Article;

public class ArticleContents {
    private final long articleId;
    private final String time;
    private final String writer;
    private final String title;
    private final String contents;

    public ArticleContents(long articleId, String time, String writer, String title, String contents) {
        this.articleId = articleId;
        this.time = time;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }

    public ArticleContents(Article article) {
        this.articleId = article.getArticleId();
        this.time = article.getTime();
        this.writer = article.getWriter();
        this.title = article.getTitle();
        this.contents = article.getContent();
    }

    public long getArticleId() { return articleId; }

    public String getTime() {
        return time;
    }

    public String getWriter() {
        return writer;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }
}
