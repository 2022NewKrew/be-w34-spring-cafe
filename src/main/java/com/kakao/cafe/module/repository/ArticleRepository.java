package com.kakao.cafe.module.repository;

import com.kakao.cafe.module.model.domain.Article;

import java.util.List;

import static com.kakao.cafe.module.model.dto.ArticleDtos.*;

public interface ArticleRepository {

    void addArticle(Article article);

    List<Article> findAllArticles();

    Article findArticleById(Long id);

    List<ArticleListDto> findAllArticlesWithAuthor();
}
