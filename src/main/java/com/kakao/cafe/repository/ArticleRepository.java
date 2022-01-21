package com.kakao.cafe.repository;


import com.kakao.cafe.dto.ArticleDTO;

import java.util.List;

public interface ArticleRepository {

    long insertArticle(ArticleDTO article);

    List<ArticleDTO> getAllArticle(Long page);

    ArticleDTO getArticleById(long id);

    int increaseViews(long articleId);

    int updateArticle(long id, ArticleDTO article);

    int deleteArticle(long id);

    Long getArticleCount();
}
