package com.kakao.cafe.service;


import com.kakao.cafe.dto.ArticleDTO;

import java.util.List;

public interface ArticleService {

    long insertArticle(ArticleDTO article);

    List<ArticleDTO> getArticleList();

    ArticleDTO getArticleById(long articleId);

    int increaseViews(long articleId);

    int updateArticle(long id, ArticleDTO article);

    int deleteArticle(long id);
}
