package com.kakao.cafe.service.dto;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class ArticleDto {

    private long id;
    private final UserDto author;
    private final String title;
    private final String content;
    private final Date createdAt;
    private final List<ReplyDto> replies;

    private ArticleDto(
            long id,
            UserDto author,
            String title,
            String content,
            List<ReplyDto> replies,
            Date createdAt
    ) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
        this.replies = replies;
        this.createdAt = createdAt;
    }

    public static class Builder {

        private long id;
        private UserDto author;
        private String title;
        private String content;
        private Date createdAt;
        private List<ReplyDto> replies;

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder author(UserDto author) {
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

        public Builder replies(List<ReplyDto> replies) {
            this.replies = Collections.unmodifiableList(replies);
            return this;
        }

        public ArticleDto build() {
            return new ArticleDto(id, author, title, content, replies, createdAt);
        }
    }
}
