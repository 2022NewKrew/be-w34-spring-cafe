package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;
import java.util.List;

public interface ArticleRepository {

    void createArticle(Article article);
    List<Article> findAllArticles();
    boolean isArticleIdUsed(Integer aid);
    Article findArticleByArticleId(Integer aid);
}
