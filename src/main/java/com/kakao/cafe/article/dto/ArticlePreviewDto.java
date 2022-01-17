package com.kakao.cafe.article.dto;

import com.kakao.cafe.article.model.Article;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArticlePreviewDto {
    private Long id;
    private String author;
    private String title;
    private String uploadTime;
}
