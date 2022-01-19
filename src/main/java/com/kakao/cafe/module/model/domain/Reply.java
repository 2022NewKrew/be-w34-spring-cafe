package com.kakao.cafe.module.model.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reply {

    private Long id;
    private Long articleId;
    private Long authorId;
    private String comment;
    private LocalDateTime created;
}
