package com.kakao.cafe.domain.entity;

// NOTE domain 레이어의 객체가 service 레이어 객체를 참조하는 게 괜찮은가?
// 그렇지 않게 하려면 ArticleDto.fromEntity(...)와 같이 접근해야 하는데
// 게터가 필요하다는 문제가 생김

import com.kakao.cafe.service.dto.ArticleDto;
import com.kakao.cafe.service.dto.ReplyDto;
import com.kakao.cafe.service.dto.UserDto;

import java.util.Date;

public class Reply {

    private final long id;
    private final User author;
    private final Article target;
    private final String content;
    private final Date createdAt;

    private Reply(
            long id,
            User author,
            Article target,
            String content,
            Date createdAt
    ) {
        this.id = id;
        this.author = author;
        this.target = target;
        this.content = content;
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }

    public ReplyDto toDto() {
        UserDto authorDto = null;
        if (author != null) {
            authorDto = author.toDto();
        }
        ArticleDto targetDto = null;
        if (target != null) {
            targetDto = target.toDto();
        }
        return new ReplyDto.Builder()
                .id(id)
                .author(authorDto)
                .target(targetDto)
                .content(content)
                .createdAt(createdAt)
                .build();
    }

    public long getAuthorId() {
        return author.getId();
    }

    public static class Builder {

        private long id;
        private User author;
        private Article target;
        private String content;
        private Date createdAt;

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder author(User author) {
            this.author = author;
            return this;
        }

        public Builder target(Article target) {
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

        public Reply build() {
            return new Reply(id, author, target, content, createdAt);
        }
    }
}
