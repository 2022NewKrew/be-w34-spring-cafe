package com.kakao.cafe.entity;

import com.kakao.cafe.dto.ArticleDto;

public class Article implements Entity<ArticleDto> {

    private final User owner;
    private final String author;
    private final String title;
    private final String content;

    private Article(User owner, String author, String title, String content) {
        this.owner = owner;
        this.author = author;
        this.title = title;
        this.content = content;
    }

    @Override
    public ArticleDto toDto() {
        return new ArticleDto.Builder()
                .owner(owner.toDto())
                .author(author)
                .title(title)
                .content(content)
                .build();
    }

    public static class Builder {

        private User owner;
        private String author;
        private String title;
        private String content;

        public Builder owner(User owner) {
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

        public Article build() {
            return new Article(owner, author, title, content);
        }
    }
}
