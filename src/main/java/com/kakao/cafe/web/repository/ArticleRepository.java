package com.kakao.cafe.web.repository;

import com.kakao.cafe.web.domain.Article;
import com.kakao.cafe.web.dto.ArticleCreateDTO;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {
    Article save(ArticleCreateDTO articleCreateDTO);
    List<Article> getArticleList();
    Optional<Article> getArticleById(long id);
}
