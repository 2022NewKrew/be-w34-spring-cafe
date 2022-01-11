package com.kakao.cafe.controller.mapper;

import com.kakao.cafe.controller.dto.ArticleDetailDto;
import com.kakao.cafe.controller.dto.ArticleItemDto;
import com.kakao.cafe.domain.Article;

public class ArticleItemDtoMapper {
    public static ArticleItemDto toArticleItemDto(Article article) {
        return new ArticleItemDto(article.getId(), article.getTitle(), article.getContents());
    }

    public static ArticleDetailDto toArticleDetailDto(Article article) {
        return new ArticleDetailDto(article.getTitle(), article.getWriter(), article.getContents());
    }
}
