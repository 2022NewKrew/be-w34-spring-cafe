package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.domain.Article;
import java.util.List;

public interface ArticleRepository {

    void save(Article article);

    Article findById(Long id);

    List<Article> findAll();

    void update(Long id, Article article);

    void delete(Long id);
}
