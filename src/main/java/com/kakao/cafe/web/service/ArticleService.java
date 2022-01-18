package com.kakao.cafe.web.service;

import com.kakao.cafe.ArticleMapper;
import com.kakao.cafe.UserMapper;
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
        String sql = "SELECT ID, AUTHOR_ID, CREATEDATE, TITLE, CONTENT FROM ARTICLES";
        List<Article> articles = jdbcTemplate.query(sql, new ArticleMapper());
        for (Article a : articles) {
            String authorQuery = "SELECT ID, USERID, PASSWORD, NAME, EMAIL FROM USERS WHERE ID=?";
            a.setAuthor(jdbcTemplate.queryForObject(authorQuery, new UserMapper(), a.getAuthorId()));
        }
        return articles;
    }

    public void addArticle(Article article) {
        String sql = "INSERT INTO ARTICLES (AUTHOR_ID, TITLE, CONTENT) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, article.getAuthorId(), article.getTitle(), article.getContent());
    }

    public Article getByArticleId(int id) {
        String sql = "SELECT ID, AUTHOR_ID, TITLE, CONTENT, CREATEDATE FROM ARTICLES WHERE ID=?";
        Article article = jdbcTemplate.queryForObject(sql, new ArticleMapper(), id);
        String authorQuery = "SELECT ID, USERID, PASSWORD, NAME, EMAIL FROM USERS WHERE ID=?";
        article.setAuthor(jdbcTemplate.queryForObject(authorQuery, new UserMapper(), article.getAuthorId()));
        return article;
    }
}
