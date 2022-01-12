package com.kakao.cafe.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class ArticleDTO {
    private long key;
    private String author;
    private String title;
    private String content;
    private String postTime;
}
