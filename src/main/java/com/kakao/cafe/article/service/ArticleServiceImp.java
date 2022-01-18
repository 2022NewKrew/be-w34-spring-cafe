package com.kakao.cafe.article.service;

import com.kakao.cafe.article.model.Article;
import com.kakao.cafe.article.model.ArticleDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ArticleServiceImp implements ArticleService{
    private List<Integer> articleIds = new ArrayList<>();
    private HashMap<Integer, Article> articleEntities = new HashMap<>();

    @Override
    public List<Article> getArticles() {
        List<Article> result = new ArrayList<>();
        for(int articleIdIndex : articleIds){
            result.add(articleEntities.get(articleIdIndex));
        }
        return result;
    }

    @Override
    public Article addArticle(Article article) {
        articleIds.add(article.getId());
        articleEntities.put(article.getId(), article);
        return article;
    }

    @Override
    public Article addArticle(ArticleDTO articleDTO) {
        Article article = new Article(articleIds.size(), articleDTO);

        articleIds.add(article.getId());
        articleEntities.put(article.getId(),article);
        return article;
    }

    @Override
    public Article readArticle(int id) {
        return articleEntities.get(id);
    }

    @Override
    public void updateArticle(int id, ArticleDTO articleDTO) {
        Article article = new Article(id, articleDTO);
        articleEntities.put(id, article);
    }

    @Override
    public void deleteArticle(int id) {
        articleIds.remove(id);
        articleEntities.remove(id);
    }
}
