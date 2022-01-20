package com.kakao.cafe.article.repo;

import com.kakao.cafe.article.model.Article;
import com.kakao.cafe.common.CrRepository;

public interface ArticleRepository extends CrRepository<Article> {
    void update(Article article);
}
