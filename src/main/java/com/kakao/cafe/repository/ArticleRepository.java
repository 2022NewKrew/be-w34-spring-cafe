package com.kakao.cafe.repository;


import com.kakao.cafe.dto.ArticleDTO;

import java.util.List;

public interface ArticleRepository {

    long insertArticle(ArticleDTO article);

    List<ArticleDTO> getAllArticle();

    ArticleDTO getArticleById(long id);

    int increaseViews(long articleId);
}
