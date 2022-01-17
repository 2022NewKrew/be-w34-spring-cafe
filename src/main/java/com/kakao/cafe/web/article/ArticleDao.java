package com.kakao.cafe.web.article;

import com.kakao.cafe.web.article.domain.Article;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArticleDao {
    private final JdbcTemplate jdbcTemplate;

    public ArticleDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Article> readArticles() {
        String sql = "SELECT ID, CONTENT, TITLE, WRITER, CREATE_DATE FROM ARTICLE";
        return jdbcTemplate.query(sql, new ArticleMapper());
    }

    public void createArticle(Article article) {
        String sql = "INSERT INTO ARTICLE (TITLE, WRITER, CONTENT) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, article.getTitle(), article.getWriter(), article.getContent());
    }

    public Article findById(int id) {
        String sql = "SELECT TITLE, WRITER, CONTENT, CREATE_DATE FROM ARTICLE WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, new ArticleMapper(), id);
    }
}
