package com.kakao.cafe.Repository;

import com.kakao.cafe.domain.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleLocalRepository implements ArticleRepository{
    List<Article> articleList;

    public ArticleLocalRepository(){
        articleList = new ArrayList<>();
    }

    @Override
    public void save(Article article) {
        articleList.add(article);
        System.out.println(article);
    }
}
