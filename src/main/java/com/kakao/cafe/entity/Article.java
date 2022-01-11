package com.kakao.cafe.entity;

import com.kakao.cafe.dto.ArticleDto;
import com.kakao.cafe.dto.UserDto;

import java.util.Date;

public class Article implements Entity<ArticleDto> {

    private long id;
    private final User owner;
    private final String author;
    private final String title;
    private final String content;
    private Date createdAt;

    private Article(
            long id,
            User owner,
            String author,
            String title,
            String content,
            Date createdAt
    ) {
        this.id = id;
        this.owner = owner;
        this.author = author;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public ArticleDto toDto() {
        UserDto ownerDto = null;
        if (owner != null) {
            ownerDto = owner.toDto();
        }
        return new ArticleDto.Builder()
                .id(id)
                .owner(ownerDto)
                .author(author)
                .title(title)
                .content(content)
                .createdAt(createdAt)
                .build();
    }

    public static class Builder {

        private long id;
        private User owner;
        private String author;
        private String title;
        private String content;
        private Date createdAt;

        public Builder id(long id) {
            this.id = id;
            return this;
        }

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

        public Builder createdAt(Date createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Article build() {
            return new Article(id, owner, author, title, content, createdAt);
        }
    }
}
