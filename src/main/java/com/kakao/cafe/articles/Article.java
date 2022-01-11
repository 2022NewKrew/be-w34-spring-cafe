package com.kakao.cafe.articles;

import java.util.Objects;

public class Article {
    private Long id;
    private String title;
    private ArticleContent content;
    private String writer;

    public Article(Long id, String title, ArticleContent content, String writer) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public ArticleContent getContent() {
        return content;
    }

    public String getWriter() {
        return writer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(title, article.title) && Objects.equals(content, article.content) && Objects.equals(writer, article.writer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, content, writer);
    }

    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", content=" + content +
                ", writer='" + writer + '\'' +
                '}';
    }
}
