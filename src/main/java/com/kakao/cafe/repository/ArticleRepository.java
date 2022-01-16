package com.kakao.cafe.repository;

import com.kakao.cafe.domain.article.ArticleList;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleRepository {
    ArticleList articleList = ArticleList.getInstance();

    public ArticleList getArticleList() {
        return articleList;
    }
}
