package com.kakao.cafe.repository;

import com.kakao.cafe.repository.dao.ArticleDAO;
import com.kakao.cafe.domain.article.Article;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ArticleRepository {

    private final ArticleDAO articleDAO;

    public ArticleRepository(ArticleDAO articleDAO) {
        this.articleDAO = articleDAO;
    }

    public void create(Article article) {
        articleDAO.create(article);
    }

    public Article findById(int id) {
        return articleDAO.findById(id);
    }

    public List<Article> getArticleList() {
        return articleDAO.findAll();
    }
}
