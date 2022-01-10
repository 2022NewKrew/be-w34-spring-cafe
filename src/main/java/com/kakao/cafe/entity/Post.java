package com.kakao.cafe.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Post extends BaseEntity {
    private Long postId;
    private String title;
    private String content;
    private String writerEmail;
    private Long viewCount;

    @Override
    public LocalDateTime getModDate() {
        return super.getModDate();
    }

    @Override
    public LocalDateTime getRegDate() {
        return super.getRegDate();
    }

    public Post init() {
        super.register();
        return this;
    }
}
