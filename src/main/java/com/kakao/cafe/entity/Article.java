package com.kakao.cafe.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Article {
    private Long articleId;
    private String title;
    private String content;
    private Long viewCount;
    private LocalDateTime regDate;
    private LocalDateTime modDate;

    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeContent(String content) {
        this.content = content;
    }

    private User writer;
}
