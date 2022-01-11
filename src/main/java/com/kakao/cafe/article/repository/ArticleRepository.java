package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.entity.Article;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ArticleRepository {

    private final List<Article> articleList;
    private Integer identity;

    private ArticleRepository() {
        this.articleList = new ArrayList<>();
        identity = 0;
    }
}
