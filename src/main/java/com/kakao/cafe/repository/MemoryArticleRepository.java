package com.kakao.cafe.repository;

import com.kakao.cafe.domain.article.Article;

import java.util.ArrayList;
import java.util.List;

public class MemoryArticleRepository implements ArticleRepository {

    private static final List<Article> articleList = new ArrayList<>();
    private static Long idNumber = 0L;

    @Override
    public Article create(Article article) {
        article.setId(++idNumber);
        articleList.add(article);
        return article;
    }

    @Override
    public List<Article> findAll() {
        return List.copyOf(articleList);
    }
}
