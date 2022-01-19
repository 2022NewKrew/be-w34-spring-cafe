package com.kakao.cafe.adapter.out.infra.persistence.article;

import com.kakao.cafe.domain.article.Article;
import java.util.List;
import java.util.Optional;

public interface ArticleRepository {

    void save(Article article);

    void update(Article article);

    List<Article> getAllArticleList();

    Optional<Article> findById(int id);
}
