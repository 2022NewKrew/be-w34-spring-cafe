package com.kakao.cafe.domain.article.dto;

import com.kakao.cafe.domain.article.Article;

public class ArticleRequestDto {
    private String author;
    private String title;
    private String content;

    public Article toArticle() {
        return new Article(author, title, content);
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
