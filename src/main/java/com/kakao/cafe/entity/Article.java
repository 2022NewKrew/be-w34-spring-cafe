package com.kakao.cafe.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Article extends BaseEntity {
    private Long articleId;
    private String title;
    private String content;
    private Long viewCount;

    @Override
    public LocalDateTime getModDate() {
        return super.getModDate();
    }

    @Override
    public LocalDateTime getRegDate() {
        return super.getRegDate();
    }

    public Article init(Long articleId) {
        super.register();
        this.articleId = articleId;
        this.viewCount = 0L;
        return this;
    }

    private User writer;
}
