package com.kakao.cafe.dto;

import com.kakao.cafe.entity.Article;

import java.util.Date;

public class ArticleDto implements Dto<Article> {

    private long id;
    private final UserDto owner;
    private final String author;
    private final String title;
    private final String content;
    private final Date createdAt;

    private ArticleDto(long id, UserDto owner, String author, String title, String content, Date createdAt) {
        this.id = id;
        this.owner = owner;
        this.author = author;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }

    @Override
    public Article toEntity() {
        return new Article.Builder()
                .id(id)
                .owner(owner.toEntity())
                .author(author)
                .title(title)
                .content(content)
                .createdAt(createdAt)
                .build();
    }

    public static class Builder {

        private long id;
        private UserDto owner;
        private String author;
        private String title;
        private String content;
        private Date createdAt;

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder owner(UserDto owner) {
            this.owner = owner;
            return this;
        }

        public Builder author(String author) {
            this.author = author;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Builder createdAt(Date createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public ArticleDto build() {
            return new ArticleDto(id, owner, author, title, content, createdAt);
        }
    }
}
