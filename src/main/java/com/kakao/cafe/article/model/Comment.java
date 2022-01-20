package com.kakao.cafe.article.model;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Comment {
    private Long id;
    @Setter
    private Long articleId;
    @Setter
    private String author;
    private String contents;
    private String uploadTime;
    private Boolean deleted;
}
