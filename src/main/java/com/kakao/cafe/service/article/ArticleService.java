package com.kakao.cafe.service.article;

import com.kakao.cafe.dto.ArticleDto;

import java.util.List;

public interface ArticleService {
    void uploadArticle(ArticleDto articleDto);

    List<ArticleDto> allArticles();

    ArticleDto retrieveArticle(Long articleId);

    void deleteArticle(Long articleId);

    void updateArticle(ArticleDto articleDto);
}
