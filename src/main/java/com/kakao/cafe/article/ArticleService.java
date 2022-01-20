package com.kakao.cafe.article;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    private final JdbcTemplate jdbcTemplate;
    private ArticleService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Articles> getArticles() {
        String sql = "SELECT * FROM ARTICLES";
        return jdbcTemplate.query(sql, new ArticleMapper());
    }

    public void createArticle(Articles article) {
        String sql = "INSERT INTO ARTICLES (AUTHOR, TITLE, CONTENT) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql,article.getAuthor(), article.getTitle(), article.getContent());
    }

    public Articles getArticleById(Long id) {
        String sql = "SELECT * FROM ARTICLES WHERE ID=?";
        return jdbcTemplate.queryForObject(sql, new ArticleMapper(), id);
    }
}
