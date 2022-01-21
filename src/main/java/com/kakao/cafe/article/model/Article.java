package com.kakao.cafe.article.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    @Setter
    private Long id;
    private String author;
    private String title;
    private String contents;
    @Setter
    private String uploadTime;
    private Boolean deleted;
}
