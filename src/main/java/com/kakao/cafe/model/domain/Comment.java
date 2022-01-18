package com.kakao.cafe.model.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Setter
    private long articleId;

    @Setter
    private long commentId;

    private String writerId;
    private String content;

    @Setter
    private LocalDateTime createdDate;
}
