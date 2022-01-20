package com.kakao.cafe.repository;

import com.kakao.cafe.domain.model.Article;
import com.kakao.cafe.domain.dto.ArticleSaveDto;

import java.util.List;

public interface ArticleRepository {
    void save(ArticleSaveDto articleSaveDTO);
    Article findArticleById(int id);
    List<Article> findAllArticles();
}
