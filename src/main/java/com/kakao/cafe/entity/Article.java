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

    private User writer;
}
