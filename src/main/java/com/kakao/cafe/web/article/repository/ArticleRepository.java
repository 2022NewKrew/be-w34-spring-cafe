package com.kakao.cafe.web.article.repository;

import com.kakao.cafe.web.article.domain.Article;
import com.kakao.cafe.web.article.dto.ArticleCreateDTO;
import com.kakao.cafe.web.article.dto.ArticleUpdateDTO;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {
    Article save(ArticleCreateDTO articleCreateDTO);
    List<Article> getArticleList();
    Optional<Article> getArticleById(long id);
    Article update(ArticleUpdateDTO articleUpdateDTO);
    void deleteArticleById(long id);
    Object getArticleListNotDeleted();
}
