package com.kakao.cafe.module.model.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Article {

    private Long id;

    @Setter
    private Long authorId;

    private String title;
    private String contents;
    private LocalDateTime created;
    private Integer viewCount;
    private Integer commentCount;
}
