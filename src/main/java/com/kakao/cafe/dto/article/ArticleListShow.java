package com.kakao.cafe.dto.article;

import com.kakao.cafe.domain.entity.Article;

public class ArticleListShow {
    private final long articleId;
    private final String time;
    private final String writer;
    private final String title;

    public ArticleListShow(long articleId, String time, String writer, String title) {
        this.articleId = articleId;
        this.time = time;
        this.writer = writer;
        this.title = title;
    }

    public ArticleListShow(ArticleWithWriterName articleWithWriterName) {
        this.articleId = articleWithWriterName.getArticleId();
        this.time = articleWithWriterName.getTime();
        this.writer = articleWithWriterName.getWriter();
        this.title = articleWithWriterName.getTitle();
    }

    public long getArticleId() {
        return articleId;
    }

    public String getTime() {
        return time;
    }

    public String getWriter() {
        return writer;
    }

    public String getTitle() {
        return title;
    }
}
