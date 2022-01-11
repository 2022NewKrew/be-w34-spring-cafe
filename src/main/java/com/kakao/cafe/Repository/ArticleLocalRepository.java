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
        int curSize = articleList.size() + 1;
        article.setId((long)curSize);
        articleList.add(article);
    }

    @Override
    public List<Article> getAllQuestions() {
        return articleList;
    }

    @Override
    public Article findById(String id) {
        return articleList.stream()
                .filter(x -> x.getId().toString().equals(id))
                .findFirst()
                .orElse(null);
    }
}
