package com.kakao.cafe.service;

import com.kakao.cafe.dao.ArticleDAO;
import com.kakao.cafe.model.Article;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ArticleService {

    private final ArticleDAO articleDAO;

    public ArticleService(ArticleDAO articleDAO) {
        this.articleDAO = articleDAO;
    }

    public List<Article> getArticleList() {
        return articleDAO.findAllArticle();
    }

    public Article filterArticleByIndex(int index) {
        return articleDAO.filterArticleByIndex(index - 1);
    }

    public void writeArticle(Article article) {
        int id = articleDAO.getArticleListSize() + 1;
        article.setId(id);
        articleDAO.writeArticle(article);
    }
}
