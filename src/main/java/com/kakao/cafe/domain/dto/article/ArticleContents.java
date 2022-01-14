package com.kakao.cafe.domain.dto.article;

import com.kakao.cafe.domain.entity.Article;

public class ArticleContents {
    private final String time;
    private final String writer;
    private final String title;
    private final String contents;

    public ArticleContents(String time, String writer, String title, String contents) {
        this.time = time;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }

    public ArticleContents(Article article) {
        this.time = article.getTime();
        this.writer = article.getWriter();
        this.title = article.getTitle();
        this.contents = article.getContents();
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

    public String getContents() {
        return contents;
    }
}
