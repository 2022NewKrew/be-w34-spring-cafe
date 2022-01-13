package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.domain.Article;
import java.util.List;

public interface ArticleRepository {

    void save(Article article);

    Article findById(Long id);

    List<Article> findAll();
}
