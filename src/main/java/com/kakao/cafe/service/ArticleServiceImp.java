package com.kakao.cafe.service;

import com.kakao.cafe.model.Article;
import com.kakao.cafe.model.DTO.ArticleDTO;
import com.kakao.cafe.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ArticleServiceImp implements ArticleService{

    private final ArticleRepository articleRepository;

    public ArticleServiceImp(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public List<Article> getArticles() {
        return articleRepository.findAll();
    }

    @Override
    public Article addArticle(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public Article addArticle(ArticleDTO articleDTO) {
        Article article = new Article(articleDTO);
        return articleRepository.save(article);
    }

    @Override
    public Article readArticle(int id) {
        return articleRepository.findById(id);
    }

    @Override
    public void updateArticle(int id, ArticleDTO articleDTO) {
        Article article = new Article(id, articleDTO);
        articleRepository.update(article);
    }

    @Override
    public void deleteArticle(int id) {
    }
}
