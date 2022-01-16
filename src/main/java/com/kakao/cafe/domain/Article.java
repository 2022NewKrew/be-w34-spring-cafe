package com.kakao.cafe.domain;

public class Article {
    private String title;
    private String content;
    private static Integer articleCounts = 0;
    private Integer articleIndex;

    public Article() {
        articleCounts++;
        articleIndex = articleCounts;
    }

    public Article(String title, String content) {
        this.title = title;
        this.content = content;

        articleCounts++;
        articleIndex = articleCounts;
    }

    public Article(Article article) {
        this.title = article.getTitle();
        this.content = article.getContent();

        articleCounts++;
        articleIndex = articleCounts;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public Integer getArticleIndex() {
        return articleIndex;
    }

    public void setContent(String content) {
        this.content = content;
    }
}



