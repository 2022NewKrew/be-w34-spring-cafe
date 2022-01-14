package com.kakao.cafe.repository;

import com.kakao.cafe.domain.model.Article;
import com.kakao.cafe.domain.dto.ArticleSaveDTO;

import java.util.List;

public interface ArticleRepository {
    void save(ArticleSaveDTO articleSaveDTO);
    Article findArticleById(int id);
    List<Article> findAllArticles();
}
