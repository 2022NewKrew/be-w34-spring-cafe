package com.kakao.cafe.web.service;

import com.kakao.cafe.ArticleMapper;
import com.kakao.cafe.domain.Article;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArticleService {
    private final JdbcTemplate jdbcTemplate;

    private ArticleService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Article> getArticles() {
        String sql = "SELECT ID, AUTHOR, CREATEDATE, TITLE, CONTENT FROM ARTICLES";
        return jdbcTemplate.query(sql, new ArticleMapper());
    }

    public void addArticle(Article article) {
        String sql = "INSERT INTO ARTICLES (AUTHOR, TITLE, CONTENT) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, article.getAuthor(), article.getTitle(), article.getContent());
    }

    public Article getByArticleId(int id) {
        String sql = "SELECT ID, AUTHOR, TITLE, CONTENT, CREATEDATE FROM ARTICLES WHERE ID=?";
        return jdbcTemplate.queryForObject(sql, new ArticleMapper(), id);
    }
}
