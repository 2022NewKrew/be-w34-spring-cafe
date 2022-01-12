package com.kakao.cafe.dao;

import com.kakao.cafe.vo.Article;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ArticleDao {

    private List<Article> articles = new ArrayList<>();

    public void addArticle(Article article) {
        articles.add(article);
    }

    public List<Article> getArticles() {
        return articles;
    }

    public Article getArticle(int index) {
        if(articles.size() >= index)
            return articles.get(index - 1);
        return null;
    }

}
