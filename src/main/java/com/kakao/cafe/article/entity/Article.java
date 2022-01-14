package com.kakao.cafe.article.entity;

import com.kakao.cafe.article.dto.request.ArticleCreateRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter @Setter
public class Article {

    private Long id;
    private String writer;
    private String title;
    private String contents;
    private LocalDateTime createdAt;
}
