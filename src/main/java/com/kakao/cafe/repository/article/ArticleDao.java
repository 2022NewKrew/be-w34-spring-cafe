package com.kakao.cafe.repository.article;

import com.kakao.cafe.domain.article.Article;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArticleDao {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Article> articleMapper = (rs, rowNum) -> new Article(
            rs.getLong("id"),
            rs.getString("title"),
            rs.getString("content"),
            rs.getString("date"));

    public ArticleDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(Article article) {
        String sql = "INSERT INTO ARTICLES (title,content,date) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, article.getTitle(), article.getContent(), article.getDate());
    }

    public Article selectById(long id) {
        String sql = "SELECT id,title,content,date FROM ARTICLES WHERE id=?";
        return jdbcTemplate.queryForObject(sql, articleMapper, id);
    }

    public List<Article> selectAll() {
        String sql = "SELECT id,title,content,date FROM ARTICLES";
        return jdbcTemplate.query(sql, articleMapper );
    }
}
