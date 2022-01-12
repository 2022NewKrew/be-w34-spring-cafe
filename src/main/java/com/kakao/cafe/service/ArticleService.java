package com.kakao.cafe.service;

import com.kakao.cafe.dao.ArticleDao;
import com.kakao.cafe.vo.Article;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    private ArticleDao articleDao;

    public ArticleService(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    public void addArticle(Article article) {
        articleDao.addArticle(article);
    }

    public List<Article> getArticles() {
        return articleDao.getArticles();
    }

    public Article getArticle(int index) {
        return articleDao.getArticle(index);
    }

}
