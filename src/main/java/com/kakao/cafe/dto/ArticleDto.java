package com.kakao.cafe.dto;

import com.kakao.cafe.entity.Article;

public class ArticleDto implements Dto<Article> {

    private final UserDto owner;
    private final String author;
    private final String title;
    private final String content;

    private ArticleDto(UserDto owner, String author, String title, String content) {
        this.owner = owner;
        this.author = author;
        this.title = title;
        this.content = content;
    }

    @Override
    public Article toEntity() {
        return new Article.Builder()
                .owner(owner.toEntity())
                .author(author)
                .title(title)
                .content(content)
                .build();
    }

    public static class Builder {

        private UserDto owner;
        private String author;
        private String title;
        private String content;

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

        public ArticleDto build() {
            return new ArticleDto(owner, author, title, content);
        }
    }
}
