package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.domain.Article;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Repository
public class ArticleMemoryRepository implements ArticleRepository {

    private static List<Article> articleList = new ArrayList<>();

    @Override
    public Article save(Article article) {
        articleList.add(article);
        return article;
    }

    @Override
    public List<Article> findAll() {
        return Collections.unmodifiableList(articleList);
    }
}
