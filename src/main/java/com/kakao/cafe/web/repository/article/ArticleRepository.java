package com.kakao.cafe.web.repository.article;

import com.kakao.cafe.web.domain.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {
    Article save(Article article);
    Optional<Article> findById(int id);
    List<Article> findAll();


}
