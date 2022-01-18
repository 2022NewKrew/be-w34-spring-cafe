package com.kakao.cafe.module.repository;

import com.kakao.cafe.module.model.domain.Article;

import java.util.List;

import static com.kakao.cafe.module.model.dto.ArticleDtos.*;

public interface ArticleRepository {

    void addArticle(Article article);

    ArticleReadDto findArticleById(Long id);

    List<ArticleListDto> findAllArticles();

    void updateArticle(Long id, String title, String contents);
}
