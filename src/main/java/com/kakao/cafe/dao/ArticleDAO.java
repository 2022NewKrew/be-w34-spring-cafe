package com.kakao.cafe.dao;

import com.kakao.cafe.model.Article;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ArticleDAO {

    private List<Article> articleList = new ArrayList<>();

    public List<Article> findAllArticle() {
        return articleList;
    }

    public void writeArticle(Article article) {
        articleList.add(article);
    }
}
