package com.kakao.cafe.service;

import com.kakao.cafe.dao.ArticleDAO;
import com.kakao.cafe.model.Article;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ArticleService {

    private ArticleDAO articleDAO;

    public ArticleService(ArticleDAO articleDAO) {
        this.articleDAO = articleDAO;
    }

    public List<Article> getArticleList() {
        return articleDAO.findAllArticle();
    }

    public void writeArticle(Article article) {
        articleDAO.writeArticle(article);
    }
}
