package com.kakao.cafe.service.dto;

import java.util.Date;

public class ReplyDto {

    private long id;
    private final UserDto author;
    private final ArticleDto target;
    private final String content;
    private final Date createdAt;

    private ReplyDto(long id, UserDto author, ArticleDto target, String content, Date createdAt) {
        this.id = id;
        this.author = author;
        this.target = target;
        this.content = content;
        this.createdAt = createdAt;
    }

    public static class Builder {

        private long id;
        private UserDto author;
        private ArticleDto target;
        private String content;
        private Date createdAt;

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder author(UserDto author) {
            this.author = author;
            return this;
        }

        public Builder ArticleDto(ArticleDto target) {
            this.target = target;
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

        public Builder target(ArticleDto target) {
            this.target = target;
            return this;
        }

        public ReplyDto build() {
            return new ReplyDto(id, author, target, content, createdAt);
        }
    }
}
