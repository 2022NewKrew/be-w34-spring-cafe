package com.kakao.cafe.dao;

import com.kakao.cafe.model.Article;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ArticleDAO {

    private final List<Article> articleList = new ArrayList<>();

    public int getArticleListSize() {
        return articleList.size();
    }

    public List<Article> findAllArticle() {
        return articleList;
    }

    public Article filterArticleByIndex(int index) {
        return articleList.get(index);
    }

    public void writeArticle(Article article) {
        articleList.add(article);
    }
}
