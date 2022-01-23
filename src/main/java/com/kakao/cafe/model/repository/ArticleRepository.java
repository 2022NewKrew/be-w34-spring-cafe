package com.kakao.cafe.model.repository;

import com.kakao.cafe.model.domain.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {
    boolean saveArticle(Article article);

    List<Article> findAllArticle();

    Optional<Article> findArticleByArticleId(long articleId);

    List<Article> findArticlesByWriterId(String writerId);

    List<Article> findArticlesByStartAndWantedCountPerPage(long start, long countPerPage);

    long countAllAvailableArticles();

    boolean modifyArticle(Article article);

    boolean deleteArticle(long articleId);

}
