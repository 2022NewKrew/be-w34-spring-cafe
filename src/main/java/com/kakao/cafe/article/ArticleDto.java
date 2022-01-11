package com.kakao.cafe.article;

public class ArticleDto {

    private final String writer;

    private final String title;

    private final String contents;

    private final String time;

    public ArticleDto(Article article) {
        this.writer = article.getWriter();
        this.title = article.getTitle();
        this.contents = article.getContents();
        this.time = article.getTime();
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

    public String getTime() {
        return time;
    }
}
