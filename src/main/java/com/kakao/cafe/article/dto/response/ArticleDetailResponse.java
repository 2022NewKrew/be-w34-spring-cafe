package com.kakao.cafe.article.dto.response;

import com.kakao.cafe.article.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ArticleDetailResponse {

    private final Long id;
    private final String writer;
    private final String title;
    private final String contents;
    private final LocalDateTime createdAt;
}
