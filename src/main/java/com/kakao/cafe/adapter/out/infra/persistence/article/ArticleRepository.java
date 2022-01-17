package com.kakao.cafe.adapter.out.infra.persistence.article;

import com.kakao.cafe.domain.article.Article;
import java.util.List;
import java.util.Optional;

public interface ArticleRepository {

    void save(Article article);

    List<ArticleVO> getAllArticleList();

    Optional<ArticleVO> findById(int id);
}
