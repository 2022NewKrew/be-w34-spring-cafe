package com.kakao.cafe.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class ArticleEntity extends BaseEntity{
    private Long id;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime writeDate;
    private int views;
    private Long userId;

    public void putArticleId(Long id) {
        this.id = id;
    }
}
