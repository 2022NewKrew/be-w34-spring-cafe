package com.kakao.cafe.article.service;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.service.dto.ArticleReadServiceResponse;

public class ArticleServiceDTOMapper {
    public static ArticleReadServiceResponse convertToArticleReadServiceResponse(Article article, String authorStringId) {
        return ArticleReadServiceResponse.builder()
                .id(article.getId())
                .authorId(article.getAuthorId())
                .authorStringId(authorStringId)
                .title(article.getTitle())
                .contents(article.getContents())
                .makeTime(article.getDate())
                .hits(article.getHits())
                .build();
    }
}
