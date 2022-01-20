package com.kakao.cafe.model.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    @Setter
    private long articleId;

    private String title;
    private String writerId;
    private String content;

    @Setter
    private LocalDateTime createdDate;

    @Setter
    private long commentsCount;

    @Setter
    private boolean isDeleted;
}
