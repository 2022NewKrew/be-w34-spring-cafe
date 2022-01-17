package com.kakao.cafe.domain.article;

import java.sql.Timestamp;

public class Article {
    private int id;
    private Title title;
    private Content content;
    private Timestamp createdAt;
    private Timestamp modifiedAt;

    public int getId() {
        return id;
    }

    public Title getTitle() {
        return title;
    }

    public Content getContent() {
        return content;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getModifiedAt() {
        return modifiedAt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setModifiedAt(Timestamp modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public boolean equals(Object obj) {
        if(!(obj instanceof Article))
            return false;

        Article article = (Article) obj;
        return article.id == id &&
                article.title.equals(title) &&
                article.content.equals(content);
    }
}
