package com.kakao.cafe.module.model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Reply {

    private Long id;
    private Long articleId;
    private Long authorId;
    private String comment;
    private LocalDateTime created;
}
