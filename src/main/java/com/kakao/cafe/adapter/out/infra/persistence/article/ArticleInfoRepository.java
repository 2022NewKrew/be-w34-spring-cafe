package com.kakao.cafe.adapter.out.infra.persistence.article;

import com.kakao.cafe.domain.article.Article;
import java.util.List;
import java.util.Optional;

public interface ArticleInfoRepository {

    void save(Article article);

    List<Article> getAllArticleList();

    Optional<Article> findByIndex(int index);
}
