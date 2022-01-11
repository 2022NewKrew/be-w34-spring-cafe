package com.kakao.cafe.web.repository;

import com.kakao.cafe.web.domain.Article;
import com.kakao.cafe.web.dto.ArticleDTO;

import java.util.List;

public interface ArticleRepository {
    Article save(ArticleDTO articleDTO);
    List<Article> getArticleList();
    Article getArticleById(int id);
}
