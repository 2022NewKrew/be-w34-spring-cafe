package com.kakao.cafe.repository;

import com.kakao.cafe.domain.article.Article;

public interface ArticleRepositoryInterface extends RepositoryInterface<Article> {
    void updateWriter(Long writerId, String writer);
}
